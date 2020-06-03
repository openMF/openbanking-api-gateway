package hu.dpc.openbanking.apigateway;

import hu.dpc.common.http.HttpUtils;
import hu.dpc.common.http.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class RequestContent {
    private final List<String> accounts = new ArrayList<>();
    private final HttpServletRequest request;
    private String loggedInUser = "";
    private String tppClientId = "";
    private String consentId = "";
    private String actionScope = "";
    private String consentType = "";

    /**
     * Try to extract values from difference levels.
     *
     * @param request
     * @throws MalformedURLException
     * @throws UnsupportedEncodingException
     */
    public RequestContent(final HttpServletRequest request) throws MalformedURLException, UnsupportedEncodingException {
        this.request = request;

        {
            final Enumeration names = request.getHeaderNames();
            while (names.hasMoreElements()) {
                final String name = (String) names.nextElement();
                debug("Header: [" + name + "]=[" + request.getHeader(name) + "]");
            }
        }

        {
            final Enumeration names = request.getParameterNames();
            while (names.hasMoreElements()) {
                final String name = (String) names.nextElement();
                debug("Param: [" + name + "]=[" + request.getParameter(name) + "]");
            }
        }

        loggedInUser = request.getParameter("loggedInUser");
        String s = "loggedInUser: [{" + loggedInUser + "}]";
        debug(s);

        final String spQueryParams = request.getParameter("spQueryParams");
        s = "spQueryParams: [{" + spQueryParams + "}]";
        debug(s);
        if (null != spQueryParams) {
            final Map<String, String> queryParams = HttpUtils.splitQuery(new URL("http://x/x?" + spQueryParams));
            extractValues(queryParams);
        }


        final String referer = request.getHeader("referer");
        if (null != referer) {
            final Map<String, String> refererParams = HttpUtils.splitQuery(new URL(referer));

            if (null != refererParams) {
                extractValues(refererParams);

                final String spQueryParams2 = refererParams.get("spQueryParams");
                s = "spQueryParams from referer: [{" + spQueryParams2 + "}]";
                debug(s);
                if (null != spQueryParams2) {
                    final Map<String, String> queryParams2 = HttpUtils.splitQuery(new URL("http://x/x?" + spQueryParams2));
                    extractValues(queryParams2);
                }
            }
        }


        {
            final String[] tmpAccounts = request.getParameterValues("accounts[]");
            if (null != tmpAccounts && tmpAccounts.length > 0) {
                for (final String acc : tmpAccounts) {
                    s = "Accounts: [" + acc + "]";
                    debug(s);
                    accounts.add(acc);
                }
            }
        }

        {
            actionScope = request.getParameter("actionScope");
        }
    }

    private static void debug(final String str) {
        System.out.println(str);
    }

    /**
     * Try to extract values if already has no value.
     *
     * @param queryParams
     */
    private void extractValues(final Map<String, String> queryParams) {
        if (null == queryParams) {
            return;
        }
        String s;
        if (StringUtils.isNullOrEmpty(loggedInUser)) {
            loggedInUser = queryParams.get("loggedInUser");
            if (!StringUtils.isNullOrEmpty(loggedInUser)) {
                s = "loggedInUser: [{" + loggedInUser + "}]";
                debug(s);
            }
        }

        if (StringUtils.isNullOrEmpty(tppClientId)) {
            tppClientId = queryParams.get("client_id");
            if (!StringUtils.isNullOrEmpty(tppClientId)) {
                s = "tppClientId: [{" + tppClientId + "}]";
                debug(s);
            }
        }

        if (StringUtils.isNullOrEmpty(consentId)) {
            consentId = queryParams.get("consentId");
            if (!StringUtils.isNullOrEmpty(consentId)) {
                s = "consentId: [{" + consentId + "}]";
                debug(s);
            }
        }

        if (StringUtils.isNullOrEmpty(consentType)) {
            consentType = queryParams.get("consentType");
            if (!StringUtils.isNullOrEmpty(consentType)) {
                s = "consentType: [{" + consentType + "}]";
                debug(s);
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

    public String getActionScope() {
        return actionScope;
    }

    public String getConsentType() {
        return consentType;
    }
}
