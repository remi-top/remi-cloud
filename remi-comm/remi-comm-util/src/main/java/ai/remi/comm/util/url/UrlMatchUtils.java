package ai.remi.comm.util.url;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 请求路径匹配工具类
 */
public class UrlMatchUtils {
    private static final AntPathMatcher matcher = new AntPathMatcher();

    /**
     * 集合内所有正则是否包含 请求路径
     * @param patterns 所有正则
     * @param requestPath 请求路径
     * @return 返回是否在包含在内
     */
    public static boolean contain(Collection<String> patterns, String requestPath){
        if (CollectionUtil.isEmpty(patterns)){
            return false;
        }
        return patterns.stream().anyMatch((pattern) -> {
            //判断请求的url是否在忽略拦截的url中
            return matcher.match(pattern, requestPath);
        });
    }
}
