package hu.dpc.openbanking.apigateway;

import hu.dpc.common.http.HttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;
import uk.org.openbanking.v3_1_2.payments.OBWriteDomesticConsentResponse3;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class FineractGatewayPayments extends FineractGateway {
    private static final Log LOG = LogFactory.getLog(FineractGatewayPayments.class);

    @Nullable
    public static OBWriteDomesticConsentResponse3 getConsent(final ServletConfig servletConfig, final HttpServletRequest request) {
        checkServletConfig(servletConfig);

        try {
            final RequestContent requestContent = new RequestContent(request);
            final Map<String, String> headers = populateHeaders(requestContent);
            final OBWriteDomesticConsentResponse3 result = HttpUtils.doGET(OBWriteDomesticConsentResponse3.class, openBankingLogicURL + reviewUrl("/payments/consents/" + requestContent.getConsentId()), headers);
            request.getSession().putValue("PaymentConsentResult", result);
            return result;
        } catch (final Exception e) {
            LOG.error("Something went wrong!", e);
        }

        return null;
    }


}
