package hu.dpc.openbanking.apigateway;

import hu.dpc.openbanking.apigateway.entities.accounts.UpdateConsentResponse;
import org.apache.commons.io.IOUtils;
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
    private static final long serialVersionUID = -6738226448116383118L;

    private static void debug(final String str) {
        System.out.println(str);
    }

    private static void writeResponse(final HttpServletResponse resp, final String content) throws IOException {
        resp.getWriter().println(content);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse resp) throws IOException {
        debug("Called: ReportAuthorizeResult");
        try {
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("utf-8");

            final String jsonResult = IOUtils.toString(request.getInputStream());
            debug("Content:\n" + jsonResult);

            final RequestContent requestContent = new RequestContent(request);
            final String actionScope = requestContent.getActionScope();

            debug("hasLoggedInUser: " + requestContent.hasLoggedInUser());
            debug("hasTppClientId: " + requestContent.hasTppClientId());
            debug("getAccounts size: " + requestContent.getAccounts().size());
            final boolean hasParams = (requestContent.hasLoggedInUser() && requestContent.hasTppClientId() && actionScope.isEmpty());
            if (!hasParams) {
                debug("No hasParams! Return status 500!");
                resp.setStatus(500);
                return;
            }

            final OBReadConsentResponse1 consentResultFromSession = (OBReadConsentResponse1) request.getSession().getAttribute("AccountConsent");
            if (null == consentResultFromSession) {
                debug("ConsentResult in session is null");
            } else {
                debug("ConsentResult in session is not null");
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
            debug("actionScope: " + actionScope);
            if ("accounts".equals(actionScope)) {
                result = FineractGatewayAccounts.updateConsent(this.getServletConfig(),
                        requestContent.getConsentId(),
                        requestContent.getLoggedInUser(),
                        updateConsentRequest);
            } else if ("payments".equals(actionScope)) {
                result = FineractGatewayPayments.updateConsent(this.getServletConfig(),
                        requestContent.getConsentId(),
                        requestContent.getLoggedInUser(),
                        updateConsentRequest);
            } else {
                debug("Unknown action scope: " + actionScope);
                result = new UpdateConsentResponse();
                result.setResponseCode(500);
                result.setRawResponse("Unknown action scope: " + actionScope);
            }
            final boolean isSuccess = 200 == result.getResponseCode();
            debug("Response code: " + result.getResponseCode());
            resp.setStatus(isSuccess ? 200 : 500);
            if (!isSuccess) {
                writeResponse(resp, result.getRawResponse());
            }
        } catch (final Exception e) {
            debug("Error while update consent! Return status 500!");
            e.printStackTrace(resp.getWriter());
            resp.setStatus(500);
        }
    }
}
