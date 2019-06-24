package hu.dpc.poc.openbanking.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dpc.poc.openbanking.accounts.hu.dpc.poc.openbanking.entities.Account;
import hu.dpc.poc.openbanking.accounts.hu.dpc.poc.openbanking.entities.AccountsResult;
import hu.dpc.poc.openbanking.accounts.hu.dpc.poc.openbanking.entities.hu.dpc.poc.openbanking.accounts.account_access_consents.AccountAccessConsentsResult;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetAccountsList {
    public static List<String> getAccountsList(String logicURL, String serverName, String username) {
        ArrayList<String> accounts = new ArrayList<>();
        try {
            URL url = new URL(logicURL + "/accounts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }


            String jsonResult = IOUtils.toString(conn.getInputStream());
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            AccountsResult result = mapper.readValue(jsonResult, AccountsResult.class);
            for (Account account : result.getAccountsData().getAccounts()) {
                accounts.add(account.getAccountId());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    /**
     * account-access-consents
     *
     * @param serverName
     * @param username
     * @return
     */
    public static AccountAccessConsentsResult getAccountAccessConsents(String logicURL, String serverName, String username) {
        ArrayList<String> accounts = new ArrayList<>();
        try {
            URL url = new URL(logicURL + "/account-acccess-consents");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }


            String jsonResult = IOUtils.toString(conn.getInputStream());
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            AccountAccessConsentsResult result = mapper.readValue(jsonResult, AccountAccessConsentsResult.class);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
