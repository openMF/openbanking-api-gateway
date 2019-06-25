package hu.dpc.poc.openbanking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import hu.dpc.poc.openbanking.entities.RestResponseCommon;
import hu.dpc.poc.openbanking.entities.account_access_consents.ConsentResult;
import hu.dpc.poc.openbanking.entities.accounts_held.AccountHeldResponse;
import hu.dpc.poc.openbanking.entities.party.PartyResponse;
import hu.dpc.poc.openbanking.entities.update_consent.UpdateConsentRequest;
import hu.dpc.poc.openbanking.entities.update_consent.UpdateConsentResponse;
import hu.dpc.poc.openbanking.helper.HttpsTrust;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class FineractGateway {
    private static final Log LOG = LogFactory.getLog(FineractGateway.class);

    @CheckForNull
    private static <T> T doGET(Class<T> type, String logicURL, String query, String consentId, String userName) {
        String s;
        try {
            URL url = new URL(logicURL + query);
            s = "GET [" + url + "]";
            LOG.info(s);
            System.out.println(s);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            HttpsTrust.INSTANCE.trust(conn);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("x-fapi-interaction-id", UUID.randomUUID().toString());
            conn.setRequestProperty("user-id", userName);
            conn.setRequestProperty("consent-id", consentId);
            conn.setDoInput(true);
            conn.setDoOutput(false);

            int responseCode = conn.getResponseCode();
            if (responseCode < 200 || responseCode > 300) {

                InputStream inputStream = conn.getErrorStream();
                String jsonResult = (null == conn.getErrorStream()) ? "" : IOUtils.toString(inputStream);
                conn.disconnect();
                throw new RuntimeException("Failed : HTTP error code : " + responseCode + "\n" + jsonResult);
            }

            String jsonResult = IOUtils.toString(conn.getInputStream());
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            T result = mapper.readValue(jsonResult, type);
            return result;
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
        }
        return null;
    }

    @CheckForNull
    private static <T extends RestResponseCommon> T doPOST(Class<T> type, String logicURL, String query, String consentId, String userName, String body) {
        try {
            URL url = new URL(logicURL + query);
            LOG.info("POST [" + url + "]");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            HttpsTrust.INSTANCE.trust(conn);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("x-fapi-interaction-id", UUID.randomUUID().toString());
            conn.setRequestProperty("user-id", userName);
            conn.setRequestProperty("consent-id", consentId);

            int responseCode = conn.getResponseCode();
            if (responseCode < 200 || responseCode > 300) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            }

            conn.setDoInput(true);
            conn.setDoOutput(true);

            final OutputStream os = conn.getOutputStream();
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            writer.write(body);

            writer.flush();
            writer.close();
            os.close();


            String jsonResult = IOUtils.toString(conn.getInputStream());
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            T result = mapper.readValue(jsonResult, type);

            result.setResponseCode(responseCode);
            result.setRawResponse(jsonResult);

            return result;
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
        }
        return null;
    }


    @CheckForNull
    public static PartyResponse getParty(HttpServletRequest request, String logicURL) {
        try {
            RequestContent requestContent = new RequestContent(request);
            return doGET(PartyResponse.class, logicURL, "/consents/" + requestContent.getConsentId() + "/party", requestContent.getConsentId(), requestContent.getLoggedInUser());
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
            e.printStackTrace();
        }

        return null;
    }


    @CheckForNull
    public static ConsentResult getConsent(HttpServletRequest request, String logicURL) {
        try {
            RequestContent requestContent = new RequestContent(request);
            return doGET(ConsentResult.class, logicURL, "/consents/" + requestContent.getConsentId(), requestContent.getConsentId(), requestContent.getLoggedInUser());
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
            e.printStackTrace();
        }

        return null;
    }

    @CheckForNull
    public static AccountHeldResponse getAccountsHeld(HttpServletRequest request, String logicURL) {
        try {
            RequestContent requestContent = new RequestContent(request);
            return doGET(AccountHeldResponse.class, logicURL, "/consents/" + requestContent.getConsentId() + "/accounts", requestContent.getConsentId(), requestContent.getLoggedInUser());
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
            e.printStackTrace();
        }

        return null;
    }

    @CheckForNull
    public static boolean updateConsent(String logicURL, String consentId, String userName, UpdateConsentRequest updateConsentRequest) {

        final ObjectMapper mapper = new ObjectMapper();
        final String json;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateConsentRequest);
            UpdateConsentResponse response = doPOST(UpdateConsentResponse.class, logicURL, "/consents/" + consentId, consentId, userName, json);
            if (null != response && response.getResponseCode() == 200) {
                return true;
            }
            return false;
        } catch (final JsonProcessingException e) {
            LOG.error("JSON serialize", e);
            return false;
        }

    }

}
