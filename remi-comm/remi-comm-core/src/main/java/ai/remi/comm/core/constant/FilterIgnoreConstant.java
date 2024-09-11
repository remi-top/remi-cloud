package ai.remi.comm.core.constant;


import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 过滤器忽略常量
 */
public class FilterIgnoreConstant {
    /**
     * 默认全部忽略的url 一般用于跟服务无关的
     */
    public static final Set<String> FILTER_COMMON_IGNORE_URL = Sets.newHashSet("/**/swagger**/**", "/**/webjars/**",
            "/**/v2/api-docs", "/**/v3/api-docs", "/**/error", "/**/doc.html", "/doc.html", "/**/favicon.ico");

    /**
     * 全局拦截器忽略的服务
     */
    public static final Set<String> AUTH_FILTER_IGNORE_SERVICE_NAME = Sets.newHashSet("remi-ids-web","remi-ams-web","remi-mps-web","remi-sso-web","remi-dfe-web","remi-wfe-web","remi-bie-web","remi-wpe-web","remi-job-web","remi-lce-web");
}
