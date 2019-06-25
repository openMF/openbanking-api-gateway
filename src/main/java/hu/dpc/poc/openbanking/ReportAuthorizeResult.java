package hu.dpc.poc.openbanking;

import hu.dpc.poc.openbanking.entities.account_access_consents.Consent;
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

        String openBankingLogicURL = this.getServletConfig().getServletContext().getInitParameter("openbanking.logic.url");
        UpdateConsentRequest updateConsentRequest = new UpdateConsentRequest();
        Consent consent = new Consent();
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

        boolean result = FineractGateway.updateConsent(openBankingLogicURL, requestContent.getConsentId(), requestContent.getLoggedInUser(), updateConsentRequest);

        resp.setStatus(result ? 200 : 500);
    }
}
