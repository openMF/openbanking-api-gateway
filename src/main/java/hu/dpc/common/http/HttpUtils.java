package hu.dpc.common.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dpc.openbanking.apigateway.entities.RestResponseCommon;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@ParametersAreNonnullByDefault
public class HttpUtils {

    private static final Log LOG = LogFactory.getLog(HttpUtils.class);

    /**
     * Split url query params
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    @Nullable
    public static Map<String, String> splitQuery(final URL url) throws UnsupportedEncodingException {
        final String urlQuery = url.getQuery();
        if (null == urlQuery) {
            return null;
        }
        final String[] pairs = urlQuery.split("&");
        final Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        for (final String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.put(key, value);
        }
        return query_pairs;
    }


    @NotNull
    public static <T extends RestResponseCommon> T doGET(final Class<T> type, final String query, final Map<String, String> headers) throws HTTPCallExecutionException {
        return call(HTTP_METHOD.GET, type, query, headers, null);
    }

    @NotNull
    public static <T extends RestResponseCommon> T call(final HTTP_METHOD method, final Class<T> type, final String query, final Map<String, String> headers, @org.jetbrains.annotations.Nullable final String body) throws HTTPCallExecutionException {
        try {
            final boolean hasBody = !StringUtils.isNullOrEmpty(body);

            final URL url = new URL(query);
            String s = method.name() + " [" + url + "]\n" + forDebugHeaders(headers) + (hasBody ? "\n" + body : "");
            LOG.info(s);
            System.out.println(s);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            HttpsTrust.INSTANCE.trust(conn);
            conn.setRequestMethod(method.name());
            for (final Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.setDoInput(true);

            if (hasBody) {
                conn.setDoOutput(true);
                final OutputStream os = conn.getOutputStream();
                os.write(body.getBytes(StandardCharsets.UTF_8));
                os.close();
            }

            // CALL
            final int responseCode = conn.getResponseCode();
            // Check streams
            InputStream resultStream = conn.getErrorStream();
            if (null == resultStream) {
                resultStream = conn.getInputStream();
            }
            final String jsonResult = (null == resultStream) ? "" : IOUtils.toString(resultStream);
            conn.disconnect();

            s = method.name() + " response [" + responseCode + "]:\n" + jsonResult;
            LOG.info(s);
            System.out.println(s);
            final ObjectMapper mapper = new ObjectMapper();
            final T result = mapper.readValue(jsonResult, type);
            result.setResponseCode(responseCode);
            result.setRawResponse(jsonResult);

            return result;
        } catch (final Exception e) {
            LOG.error("Something went wrong!", e);
            throw new HTTPCallExecutionException(e);
        }
    }

    /**
     * Get headers values for printing debug purposes.
     *
     * @param headers
     * @return
     */
    private static String forDebugHeaders(final Map<String, String> headers) {
        final StringBuilder sbuf = new StringBuilder(1024);
        for (final Map.Entry<String, String> item : headers.entrySet()) {
            sbuf.append(item.getKey()).append(": ").append(item.getValue()).append("\n");
        }
        return sbuf.toString();
    }

    public enum HTTP_METHOD {
        GET, POST, PUT
    }

}
