package ai.remi.comm.util.http;

/**
 * url工具类
 */
public class UrlUtils {

    /**
     * @return {@code true} if the url is an http: url.
     */
    public static boolean isHttpUrl(String url) {
        return (null != url)
                && (url.length() > 6)
                && url.substring(0, 7).equalsIgnoreCase("http://");
    }

    /**
     * @return {@code true} if the url is an https: url.
     */
    public static boolean isHttpsUrl(String url) {
        return (null != url)
                && (url.length() > 7)
                && url.substring(0, 8).equalsIgnoreCase("https://");
    }


}
