package hu.dpc.openbanking.apigateway;

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
        final String jsonResult = IOUtils.toString(request.getInputStream());
        System.out.println("Content: \n" + jsonResult);

        final RequestContent requestContent = new RequestContent(request);

        final boolean hasParams = (requestContent.hasLoggedInUser() && requestContent.hasTppClientId() && !requestContent.getAccounts().isEmpty());
        if (!hasParams) {
            resp.setStatus(500);
        }

        final OBReadConsentResponse1 consentResult = (OBReadConsentResponse1) request.getSession().getValue(OBReadConsentResponse1.class.getName());
        if (null == consentResult) {
            System.out.println("ConsentResult in session is null");
        } else {
            System.out.println("ConsentResult in session is not null");
        }

        final OBReadConsentResponse1 updateConsentRequest = new OBReadConsentResponse1();
        final OBReadConsentResponse1Data consent = new OBReadConsentResponse1Data();

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
        updateConsentRequest.setData(consent);

        final boolean result = FineractGatewayAccounts.updateConsent(this.getServletConfig(), requestContent.getConsentId(), requestContent.getLoggedInUser(), updateConsentRequest);

        resp.setStatus(result ? 200 : 500);
    }
}
