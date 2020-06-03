package hu.dpc.common.http;

public class StringUtils {

    public static boolean isNullOrEmpty(final String content) {
        return null == content || content.isEmpty();
    }

    public static void debug(final String str) {
        System.out.println("DEBUG: " + str);
    }

    public static void info(final String str) {
        System.out.println("INFO: " + str);
    }

    public static void error(final String str) {
        System.out.println("ERROR: " + str);
    }

    public static void error(final String str, final Exception e) {
        System.out.println("ERROR: " + str);
        e.printStackTrace();
    }
}
