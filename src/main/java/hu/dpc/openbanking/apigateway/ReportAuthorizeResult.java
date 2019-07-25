package hu.dpc.openbanking.apigateway;

import hu.dpc.openbanking.apigateway.entities.accounts.UpdateConsentResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.org.openbanking.v3_1_2.accounts.OBReadConsentResponse1;
import uk.org.openbanking.v3_1_2.accounts.OBReadConsentResponse1Data;
import uk.org.openbanking.v3_1_2.commons.Account;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportAuthorizeResult extends HttpServlet {
    private static final Log LOG = LogFactory.getLog(ReportAuthorizeResult.class);

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse resp) throws IOException {
        try {
            final String jsonResult = IOUtils.toString(request.getInputStream());
            System.out.println("Content: \n" + jsonResult);

            final RequestContent requestContent = new RequestContent(request);

            final boolean hasParams = (requestContent.hasLoggedInUser() && requestContent.hasTppClientId() && !requestContent.getAccounts().isEmpty());
            if (!hasParams) {
                resp.setStatus(500);
            }

            final OBReadConsentResponse1 consentResultFromSession = (OBReadConsentResponse1) request.getSession().getValue("AccountConsent");
            if (null == consentResultFromSession) {
                System.out.println("ConsentResult in session is null");
            } else {
                System.out.println("ConsentResult in session is not null");
            }

            final OBReadConsentResponse1 updateConsentRequest = new OBReadConsentResponse1();
            final OBReadConsentResponse1Data consent = new OBReadConsentResponse1Data();

            if (null != consentResultFromSession) {
                consent.setPermissions(consentResultFromSession.getData().getPermissions());
            }

            if (null != requestContent.getAccounts() && !requestContent.getAccounts().isEmpty()) {
                final List<Account> accounts = new ArrayList<>();
                for (final String acc : requestContent.getAccounts()) {
                    final Account account = new Account();
                    account.setAccountId(acc);
                    accounts.add(account);
                }
                if (!accounts.isEmpty()) {
                    consent.setAccounts(accounts);
                }
            }
            consent.setAction("Authorize");
            consent.setConsentId(requestContent.getConsentId());
            updateConsentRequest.setData(consent);

            final UpdateConsentResponse result;
            final String actionScope = requestContent.getActionScope();
            if ("accounts".equals(actionScope)) {
                result = FineractGatewayAccounts.updateConsent(this.getServletConfig(), requestContent.getConsentId(), requestContent.getLoggedInUser(), updateConsentRequest);
            } else if ("payments".equals(actionScope)) {
                result = FineractGatewayPayments.updateConsent(this.getServletConfig(), requestContent.getConsentId(), requestContent.getLoggedInUser(), updateConsentRequest);
            } else {
                result = new UpdateConsentResponse();
                result.setResponseCode(500);
                result.setRawResponse("Unknown action scope: " + actionScope);
            }
            final boolean isSuccess = 200 == result.getResponseCode();
            resp.setStatus(isSuccess ? 200 : 500);
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("utf-8");
            if (!isSuccess) {
                resp.getWriter().println(result.getRawResponse());
            }
        } catch (final Exception e) {
            LOG.warn("Error while update consent", e);
            resp.setStatus(500);
        }

    }
}
