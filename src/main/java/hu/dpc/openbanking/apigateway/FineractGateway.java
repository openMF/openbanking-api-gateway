package hu.dpc.openbanking.apigateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dpc.common.http.HttpUtils;
import hu.dpc.openbanking.apigateway.entities.account_access_consents.ConsentResult;
import hu.dpc.openbanking.apigateway.entities.accounts_held.AccountHeldResponse;
import hu.dpc.openbanking.apigateway.entities.party.PartyResponse;
import hu.dpc.openbanking.apigateway.entities.update_consent.UpdateConsentRequest;
import hu.dpc.openbanking.apigateway.entities.update_consent.UpdateConsentResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FineractGateway {
    private static final Log LOG = LogFactory.getLog(FineractGateway.class);

    private static String openBankingLogicURL = null;
    private static String mockParamName = null;
    private static String mockParamValue = null;

    @Nullable
    public static PartyResponse getParty(final ServletConfig servletConfig, final HttpServletRequest request) {
        checkServletConfig(servletConfig);

        try {
            final RequestContent requestContent = new RequestContent(request);
            final Map<String, String> headers = populateHeaders(requestContent);

            return HttpUtils.doGET(PartyResponse.class, openBankingLogicURL + reviewUrl("/consents/" + requestContent.getConsentId() + "/party"), headers);
        } catch (final Exception e) {
            LOG.error("Something went wrong!", e);
        }

        return null;
    }

    private static void checkServletConfig(final ServletConfig servletConfig) {
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


    /**
     * Revirew url, if specified add Mock params to get right Postman response.
     *
     * @param url
     * @return
     */
    private static String reviewUrl(final String url) {
        if (null != mockParamName) {
            return new StringBuilder().append(url).append('?').append(mockParamName).append('=').append(mockParamValue).toString();
        }
        return url;
    }

    @NotNull
    private static Map<String, String> populateHeaders(final RequestContent requestContent) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("x-fapi-interaction-id", UUID.randomUUID().toString());
        headers.put("user-id", requestContent.getLoggedInUser());
        headers.put("consent-id", requestContent.getConsentId());
        return headers;
    }

    @Nullable
    public static ConsentResult getConsent(final ServletConfig servletConfig, final HttpServletRequest request) {
        checkServletConfig(servletConfig);

        try {
            final RequestContent requestContent = new RequestContent(request);
            final Map<String, String> headers = populateHeaders(requestContent);
            final ConsentResult result = HttpUtils.doGET(ConsentResult.class, openBankingLogicURL + reviewUrl("/consents/" + requestContent.getConsentId()), headers);
            request.getSession().putValue("ConsentResult", result);
            return result;
        } catch (final Exception e) {
            LOG.error("Something went wrong!", e);
        }

        return null;
    }

    @Nullable
    public static AccountHeldResponse getAccountsHeld(final ServletConfig servletConfig, final HttpServletRequest request) {
        checkServletConfig(servletConfig);

        try {
            final RequestContent requestContent = new RequestContent(request);
            final Map<String, String> headers = populateHeaders(requestContent);
            return HttpUtils.doGET(AccountHeldResponse.class, openBankingLogicURL + reviewUrl("/consents/" + requestContent.getConsentId() + "/accounts"), headers);
        } catch (final Exception e) {
            LOG.error("Something went wrong!", e);
        }

        return null;
    }

    public static boolean updateConsent(final ServletConfig servletConfig, final String consentId, final String userName, final UpdateConsentRequest updateConsentRequest) {
        checkServletConfig(servletConfig);

        final ObjectMapper mapper = new ObjectMapper();
        try {
            final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateConsentRequest);

            final Map<String, String> headers = new HashMap<>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            headers.put("x-fapi-interaction-id", UUID.randomUUID().toString());
            headers.put("user-id", userName);
            headers.put("consent-id", consentId);

            final UpdateConsentResponse response = HttpUtils.call(HttpUtils.HTTP_METHOD.PUT, UpdateConsentResponse.class, openBankingLogicURL + reviewUrl("/consents/" + consentId), headers, json);
            return response.getResponseCode() == 200;
        } catch (final Exception e) {
            LOG.error("Error on updateConsent", e);
            return false;
        }
    }

}
