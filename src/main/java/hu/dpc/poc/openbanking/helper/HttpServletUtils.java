package hu.dpc.poc.openbanking.helper;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpServletUtils {
    public static Map<String, String> splitQuery(final URL url) throws UnsupportedEncodingException {
        String urlQuery = url.getQuery();
        if (null == urlQuery) {
            return null;
        }
        final String[] pairs = urlQuery.split("&");
        final Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.put(key, value);
        }
        return query_pairs;
    }
}
