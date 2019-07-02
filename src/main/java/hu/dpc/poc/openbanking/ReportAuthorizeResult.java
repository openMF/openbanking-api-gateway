package hu.dpc.poc.openbanking;

import hu.dpc.poc.openbanking.entities.account_access_consents.Consent;
import hu.dpc.poc.openbanking.entities.account_access_consents.ConsentResult;
import hu.dpc.poc.openbanking.entities.accounts_held.Account;
import hu.dpc.poc.openbanking.entities.update_consent.UpdateConsentRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportAuthorizeResult extends HttpServlet {
    private static final Log LOG = LogFactory.getLog(ReportAuthorizeResult.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String jsonResult = IOUtils.toString(request.getInputStream());
        System.out.println("Content: \n" + jsonResult);

        RequestContent requestContent = new RequestContent(request);

        boolean hasParams = (requestContent.hasLoggedInUser() && requestContent.hasTppClientId() && !requestContent.getAccounts().isEmpty());
        if (!hasParams) {
            resp.setStatus(500);
        }

        ConsentResult consentResult = (ConsentResult) request.getSession().getValue("ConsentResult");
        if (null == consentResult) {
            System.out.println("ConsentResult in session is null");
        } else {
            System.out.println("ConsentResult in session is not null");
        }

        UpdateConsentRequest updateConsentRequest = new UpdateConsentRequest();
        Consent consent = new Consent();

        if (null != consentResult) {
            consent.setPermissions(consentResult.getData().getPermissions());
        }

        List<Account> accounts = new ArrayList<>();
        for (String acc : requestContent.getAccounts()) {
            Account account = new Account();
            account.setAccountId(acc);
            accounts.add(account);
        }
        consent.setAccounts(accounts);
        consent.setAction("Authorize");
        consent.setConsentId(requestContent.getConsentId());
        updateConsentRequest.setConsent(consent);

        boolean result = FineractGateway.updateConsent(this.getServletConfig(), requestContent.getConsentId(), requestContent.getLoggedInUser(), updateConsentRequest);

        resp.setStatus(result ? 200 : 500);
    }
}
