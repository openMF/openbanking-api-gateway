package hu.dpc.openbanking.apigateway;

import hu.dpc.common.http.HttpUtils;
import hu.dpc.openbanking.apigateway.entities.accounts.PartyResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static hu.dpc.common.http.StringUtils.*;

public class FineractGateway {
    static String openBankingLogicURL = null;
    private static String mockParamName = null;
    private static String mockParamValue = null;

    @Nullable
    public static PartyResponse getParty(final ServletConfig servletConfig, final HttpServletRequest request) {
        debug("Called FineractGateway.getParty");
        checkServletConfig(servletConfig);

        try {
            final RequestContent requestContent = new RequestContent(request);
            final Map<String, String> headers = populateHeaders(requestContent);

            return HttpUtils.doGET(PartyResponse.class, openBankingLogicURL + reviewUrl("/consents/" + requestContent.getConsentId() + "/party"), headers);
        } catch (final Exception e) {
            error("Something went wrong!", e);
        }

        return null;
    }

    protected static void checkServletConfig(final ServletConfig servletConfig) {
        if (null == openBankingLogicURL) {
            openBankingLogicURL = servletConfig.getServletContext().getInitParameter("openbanking.logic.url");
            info("openbanking.logic.url=[" + openBankingLogicURL + "]");
            mockParamName = servletConfig.getServletContext().getInitParameter("openbanking.logic.mock.param-name");
            info("openbanking.logic.mock.param-name=[" + mockParamName + "]");
            mockParamValue = servletConfig.getServletContext().getInitParameter("openbanking.logic.mock.param-value");

            if (null != mockParamName && null == mockParamValue) {
                mockParamValue = "";
            }
            info("openbanking.logic.mock.param-value=[" + mockParamValue + "]");
        }
    }


    /**
     * Revirew url, if specified add Mock params to get right Postman response.
     *
     * @param url
     * @return
     */
    protected static String reviewUrl(final String url) {
        if (null != mockParamName) {
            return new StringBuilder().append(url).append('?').append(mockParamName).append('=').append(mockParamValue).toString();
        }
        return url;
    }

    @NotNull
    protected static Map<String, String> populateHeaders(final RequestContent requestContent) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("x-fapi-interaction-id", UUID.randomUUID().toString());
        headers.put("user-id", requestContent.getLoggedInUser());
        headers.put("consent-id", requestContent.getConsentId());
        return headers;
    }

}
