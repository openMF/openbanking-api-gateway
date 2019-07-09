package hu.dpc.openbanking.apigateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dpc.common.http.HttpUtils;
import hu.dpc.openbanking.apigateway.entities.accounts.AccountHeldResponse;
import hu.dpc.openbanking.apigateway.entities.accounts.ConsentResult;
import hu.dpc.openbanking.apigateway.entities.accounts.UpdateConsentRequest;
import hu.dpc.openbanking.apigateway.entities.accounts.UpdateConsentResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FineractGatewayAccounts extends FineractGateway {
    private static final Log LOG = LogFactory.getLog(FineractGatewayAccounts.class);

    @Nullable
    public static ConsentResult getConsent(final ServletConfig servletConfig, final HttpServletRequest request) {
        checkServletConfig(servletConfig);

        try {
            final RequestContent requestContent = new RequestContent(request);
            final Map<String, String> headers = populateHeaders(requestContent);
            final ConsentResult result = HttpUtils.doGET(ConsentResult.class, openBankingLogicURL + reviewUrl("/consents/" + requestContent.getConsentId()), headers);
            request.getSession().putValue(ConsentResult.class.getName(), result);
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
