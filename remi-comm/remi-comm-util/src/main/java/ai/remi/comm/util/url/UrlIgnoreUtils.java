package ai.remi.comm.util.url;

import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.Set;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 请求路径忽略工具类
 */
public class UrlIgnoreUtils {

    private static final AntPathMatcher matcher = new AntPathMatcher();

    public static boolean isIgnoreUrl(Set<String> ignoreUrl, String requestPath) {
        return ignoreUrl.stream().anyMatch((url) -> {
            //判断请求的url是否在忽略拦截的url中
            return matcher.match(url, requestPath);
        });
    }

    
    public static boolean isIgnoreUrl(List<String> ignoreUrl, String requestPath) {
        return ignoreUrl.stream().anyMatch((url) -> {
            //判断请求的url是否在忽略拦截的url中
            return matcher.match(url, requestPath);
        });
    }
}
