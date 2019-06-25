package hu.dpc.poc.openbanking;

import hu.dpc.poc.openbanking.helper.HttpServletUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class RequestContent {
    private static final Log LOG = LogFactory.getLog(RequestContent.class);

    private String loggedInUser = "";
    private String tppClientId = "";
    private String consentId = "";
    private List<String> accounts = new ArrayList<>();
    private HttpServletRequest request;

    public RequestContent(HttpServletRequest request) throws MalformedURLException, UnsupportedEncodingException {
        this.request = request;

        {
            Enumeration names = request.getHeaderNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                System.out.println("Header: [" + name + "]=[" + request.getHeader(name) + "]");
            }
        }

        {
            Enumeration names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                System.out.println("Param: [" + name + "]=[" + request.getParameter(name) + "]");
            }
        }

        loggedInUser = request.getParameter("loggedInUser");
        String s = "loggedInUser: [{" + loggedInUser + "}]";
        System.out.println(s);
        LOG.info(s);

        String spQueryParams = request.getParameter("spQueryParams");
        s = "spQueryParams: [{" + spQueryParams + "}]";
        System.out.println(s);
        LOG.info(s);
        if (null != spQueryParams) {
            Map<String, String> queryParams = HttpServletUtils.splitQuery(new URL("http://x/x?" + spQueryParams));

            tppClientId = queryParams.get("client_id");
            s = "tppClientId: [{" + tppClientId + "}]";
            System.out.println(s);
            LOG.info(s);

            consentId = queryParams.get("consentId");
            s = "consentId: [{" + consentId + "}]";
            System.out.println(s);
            LOG.info(s);
        }


        String referer = request.getHeader("referer");
        if (null != referer) {
            Map<String, String> refererParams = HttpServletUtils.splitQuery(new URL(referer));

            if (null == loggedInUser || loggedInUser.isEmpty()) {
                loggedInUser = refererParams.get("loggedInUser");
                s = "loggedInUser from referer: [{" + loggedInUser + "}]";
                System.out.println(s);
                LOG.info(s);
            }

            if (null == tppClientId || tppClientId.isEmpty()) {
                tppClientId = refererParams.get("client_id");
                s = "tppClientId from referer: [{" + tppClientId + "}]";
                System.out.println(s);
                LOG.info(s);
            }

            if (null == consentId || consentId.isEmpty()) {
                consentId = refererParams.get("consentId");
                s = "consentId from referer: [{" + consentId + "}]";
                System.out.println(s);
                LOG.info(s);
            }

            String spQueryParams2 = refererParams.get("spQueryParams");
            s = "spQueryParams from referer: [{" + spQueryParams2 + "}]";
            System.out.println(s);
            LOG.info(s);
            if (null != spQueryParams2) {
                Map<String, String> queryParams2 = HttpServletUtils.splitQuery(new URL("http://x/x?" + spQueryParams2));

                if (null == tppClientId || tppClientId.isEmpty()) {
                    tppClientId = queryParams2.get("client_id");
                    s = "tppClientId from referer spQ: [{" + tppClientId + "}]";
                    System.out.println(s);
                    LOG.info(s);
                }

                if (null == consentId || consentId.isEmpty()) {
                    consentId = queryParams2.get("consentId");
                    s = "consentId referer spQ: [{" + consentId + "}]";
                    System.out.println(s);
                    LOG.info(s);
                }
            }
        }


        {
            String[] tmpAccounts = request.getParameterValues("accounts[]");
            if (tmpAccounts != null && tmpAccounts.length > 0) {
                for (String acc : tmpAccounts) {
                    s = "Accounts: [" + acc + "]";
                    System.out.println(s);
                    LOG.info(s);
                    accounts.add(acc);
                }
            }
        }
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public String getTppClientId() {
        return tppClientId;
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public boolean hasLoggedInUser() {
        return (null != loggedInUser && !loggedInUser.isEmpty());
    }

    public boolean hasTppClientId() {
        return (null != tppClientId && !tppClientId.isEmpty());
    }

    public String getConsentId() {
        return consentId;
    }
}
