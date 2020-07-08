package hu.dpc.openbanking.apigateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dpc.common.http.HTTPCallExecutionException;
import hu.dpc.common.http.HttpUtils;
import hu.dpc.openbanking.apigateway.entities.RestResponseCommon;
import hu.dpc.openbanking.apigateway.entities.accounts.UpdateConsentResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.org.openbanking.v3_1_2.accounts.OBReadConsentResponse1;
import uk.org.openbanking.v3_1_2.payments.OBWriteDomesticConsentResponse3;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static hu.dpc.common.http.StringUtils.debug;
import static hu.dpc.common.http.StringUtils.error;

public class FineractGatewayPayments extends FineractGateway {
    @Nullable
    public static OBWriteDomesticConsentResponse3 getConsent(final ServletConfig servletConfig, final HttpServletRequest request) {
        debug("Called FineractGatewayPayments.getConsent");
        checkServletConfig(servletConfig);

        try {
            final RequestContent requestContent = new RequestContent(request);
            final Map<String, String> headers = populateHeaders(requestContent);
            // Init consent
            @NotNull final RestResponseCommon initPaymentResult = HttpUtils.call(HttpUtils.HTTP_METHOD.POST, RestResponseCommon.class, openBankingLogicURL + reviewUrl("/pis-consents/" + requestContent.getConsentId()), headers, null);
            final int initPaymentResponseCode = initPaymentResult.getResponseCode();
            if (initPaymentResponseCode < 200 || initPaymentResponseCode >= 300) {
                error("Payment initialisation error!");
                return null;
            }
            // Get consent
            return HttpUtils.doGET(OBWriteDomesticConsentResponse3.class, openBankingLogicURL + reviewUrl("/pis-consents/" + requestContent.getConsentId()), headers);
        } catch (final Exception e) {
            error("Something went wrong!", e);
        }

        return null;
    }

    public static UpdateConsentResponse updateConsent(final ServletConfig servletConfig, final String consentId, final String userName, final OBReadConsentResponse1 updateConsentRequest) throws HTTPCallExecutionException {
        debug("Called FineractGatewayPayments.updateConsent");
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

            return HttpUtils.call(HttpUtils.HTTP_METHOD.PUT, UpdateConsentResponse.class, openBankingLogicURL + reviewUrl("/pis-consents/" + consentId), headers, json);
        } catch (final Exception e) {
            error("Error on updateConsent", e);
            throw new HTTPCallExecutionException(e);
        }
    }


}
