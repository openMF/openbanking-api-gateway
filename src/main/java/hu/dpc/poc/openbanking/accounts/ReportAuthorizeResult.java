package hu.dpc.poc.openbanking.accounts;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportAuthorizeResult extends HttpServlet {
    private static Logger LOG = Logger.getLogger(ReportAuthorizeResult.class);

    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        final Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        final String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.put(key, value);
        }
        return query_pairs;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String loggedInUser = "";
        String tppClientId = "";
        List<String> accounts = new ArrayList<>();

        {
            String referer = request.getHeader("referer");
            Map<String, String> refererParams = splitQuery(new URL(referer));

            loggedInUser = refererParams.get("loggedInUser");
            String spQueryParams = refererParams.get("spQueryParams");

            Map<String, String> queryParams = splitQuery(new URL("http://x/x?" + spQueryParams));
            tppClientId = queryParams.get("client_id");
        }

        {
            String[] tmpAccounts = request.getParameterValues("accounts[]");
            if (tmpAccounts != null && tmpAccounts.length > 0) {
                for (String acc : tmpAccounts) {
                    System.out.println("ACCOUNTS: " + acc);
                    accounts.add(acc);
                }
            }
        }

        int status = (null != loggedInUser && !loggedInUser.isEmpty()) && (null != tppClientId && !tppClientId.isEmpty()) && !accounts.isEmpty() ? 200 : 500;
        resp.setStatus(status);
    }
}
