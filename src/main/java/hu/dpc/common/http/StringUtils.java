package hu.dpc.common.http;

public class StringUtils {

    public static boolean isNullOrEmpty(final String content) {
        return null == content || content.isEmpty();
    }
}
