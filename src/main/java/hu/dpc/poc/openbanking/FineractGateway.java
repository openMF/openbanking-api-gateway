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

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class FineractGateway {
    private static final Log LOG = LogFactory.getLog(FineractGateway.class);

    private static String openBankingLogicURL = null;
    private static String mockParamName = null;
    private static String mockParamValue = null;

    @CheckForNull
    public static PartyResponse getParty(ServletConfig servletConfig, HttpServletRequest request) {
        checkServletConfig(servletConfig);

        try {
            RequestContent requestContent = new RequestContent(request);
            return doGET(PartyResponse.class, openBankingLogicURL, reviewUrl("/consents/" + requestContent.getConsentId() + "/party"), requestContent.getConsentId(), requestContent.getLoggedInUser());
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
            e.printStackTrace();
        }

        return null;
    }

    private static void checkServletConfig(ServletConfig servletConfig) {
        if (null == openBankingLogicURL) {
            synchronized (LOG) {
                openBankingLogicURL = servletConfig.getServletContext().getInitParameter("openbanking.logic.url");
                LOG.info("openbanking.logic.url=[" + openBankingLogicURL + "]");
                mockParamName = servletConfig.getServletContext().getInitParameter("openbanking.logic.mock.param-name");
                LOG.info("openbanking.logic.mock.param-name=[" + mockParamName + "]");
                mockParamValue = servletConfig.getServletContext().getInitParameter("openbanking.logic.mock.param-value");

                if (null != mockParamName && null == mockParamValue) {
                    mockParamValue = "";
                }
                LOG.info("openbanking.logic.mock.param-value=[" + mockParamValue + "]");
            }
        }
    }

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

            s = "GET response:\n" + jsonResult;
            LOG.info(s);
            System.out.println(s);

            ObjectMapper mapper = new ObjectMapper();
            T result = mapper.readValue(jsonResult, type);
            return result;
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
        }
        return null;
    }

    /**
     * Revirew url, if specified add Mock params to get right Postman response.
     *
     * @param url
     * @return
     */
    private static String reviewUrl(String url) {
        if (null != mockParamName) {
            return new StringBuilder(url).append('?').append(
                    mockParamName).append('=').append(mockParamValue).toString();
        }
        return url;
    }

    @CheckForNull
    public static ConsentResult getConsent(ServletConfig servletConfig, HttpServletRequest request) {
        checkServletConfig(servletConfig);

        try {
            RequestContent requestContent = new RequestContent(request);
            final ConsentResult result = doGET(ConsentResult.class, openBankingLogicURL, reviewUrl("/consents/" + requestContent.getConsentId()), requestContent.getConsentId(), requestContent.getLoggedInUser());
            request.getSession().putValue("ConsentResult", result);
            return result;
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
            e.printStackTrace();
        }

        return null;
    }

    @CheckForNull
    public static AccountHeldResponse getAccountsHeld(ServletConfig servletConfig, HttpServletRequest request) {
        checkServletConfig(servletConfig);

        try {
            RequestContent requestContent = new RequestContent(request);
            return doGET(AccountHeldResponse.class, openBankingLogicURL, reviewUrl("/consents/" + requestContent.getConsentId() + "/accounts"), requestContent.getConsentId(), requestContent.getLoggedInUser());
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
            e.printStackTrace();
        }

        return null;
    }

    @CheckForNull
    public static boolean updateConsent(ServletConfig servletConfig, String consentId, String userName, UpdateConsentRequest updateConsentRequest) {
        checkServletConfig(servletConfig);

        final ObjectMapper mapper = new ObjectMapper();
        final String json;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateConsentRequest);
            UpdateConsentResponse response = doPUT(UpdateConsentResponse.class, openBankingLogicURL, reviewUrl("/consents/" + consentId), consentId, userName, json);
            if (null != response && response.getResponseCode() == 200) {
                return true;
            }
            return false;
        } catch (final JsonProcessingException e) {
            LOG.error("JSON serialize", e);
            return false;
        }

    }

    @CheckForNull
    private static <T extends RestResponseCommon> T doPUT(Class<T> type, String logicURL, String query, String consentId, String userName, String body) {
        try {
            URL url = new URL(logicURL + query);
            String s = "PUT [" + url + "]\n" + body;
            LOG.info(s);
            System.out.println(s);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            HttpsTrust.INSTANCE.trust(conn);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("x-fapi-interaction-id", UUID.randomUUID().toString());
            conn.setRequestProperty("user-id", userName);
            conn.setRequestProperty("consent-id", consentId);

            conn.setDoInput(true);
            conn.setDoOutput(true);

            final OutputStream os = conn.getOutputStream();
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.close();


            String jsonResult = IOUtils.toString(conn.getInputStream());
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            T result = mapper.readValue(jsonResult, type);

            int responseCode = conn.getResponseCode();
            result.setResponseCode(responseCode);
            result.setRawResponse(jsonResult);

            return result;
        } catch (Exception e) {
            LOG.error("Something went wrong!", e);
        }
        return null;
    }


}
