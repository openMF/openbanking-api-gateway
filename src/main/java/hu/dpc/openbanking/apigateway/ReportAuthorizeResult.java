package hu.dpc.openbanking.apigateway;

import hu.dpc.openbanking.apigateway.entities.accounts.ConsentResult;
import hu.dpc.openbanking.apigateway.entities.accounts.UpdateConsentRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.org.openbanking.v3_1_2.accounts.Consent;
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
        final String jsonResult = IOUtils.toString(request.getInputStream());
        System.out.println("Content: \n" + jsonResult);

        final RequestContent requestContent = new RequestContent(request);

        final boolean hasParams = (requestContent.hasLoggedInUser() && requestContent.hasTppClientId() && !requestContent.getAccounts().isEmpty());
        if (!hasParams) {
            resp.setStatus(500);
        }

        final hu.dpc.openbanking.apigateway.entities.accounts.ConsentResult consentResult = (hu.dpc.openbanking.apigateway.entities.accounts.ConsentResult) request.getSession().getValue(ConsentResult.class.getName());
        if (null == consentResult) {
            System.out.println("ConsentResult in session is null");
        } else {
            System.out.println("ConsentResult in session is not null");
        }

        final UpdateConsentRequest updateConsentRequest = new UpdateConsentRequest();
        final Consent consent = new Consent();

        if (null != consentResult) {
            consent.setPermissions(consentResult.getData().getPermissions());
        }

        final List<Account> accounts = new ArrayList<>();
        for (final String acc : requestContent.getAccounts()) {
            final Account account = new Account();
            account.setAccountId(acc);
            accounts.add(account);
        }
        consent.setAccounts(accounts);
        consent.setAction("Authorize");
        consent.setConsentId(requestContent.getConsentId());
        updateConsentRequest.setConsent(consent);

        final boolean result = FineractGatewayAccounts.updateConsent(this.getServletConfig(), requestContent.getConsentId(), requestContent.getLoggedInUser(), updateConsentRequest);

        resp.setStatus(result ? 200 : 500);
    }
}
