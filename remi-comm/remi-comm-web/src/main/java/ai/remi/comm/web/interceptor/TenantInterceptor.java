package ai.remi.comm.web.interceptor;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import ai.remi.comm.core.constant.FilterIgnoreConstant;
import ai.remi.comm.util.auth.TenantHolder;
import ai.remi.comm.util.url.UrlIgnoreUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc TenantInterceptor
 */
@Slf4j
@Component
public class TenantInterceptor implements HandlerInterceptor {


    /**
     * 不拦截的url
     */
    private Set<String> ignoreUrlSet = Sets.newConcurrentHashSet();



    @Value("${sql-intercept.tenant.anonUrlSet:''}")
    private String anonUrlSet;

    @PostConstruct
    public void init() {
        ignoreUrlSet.addAll(FilterIgnoreConstant.FILTER_COMMON_IGNORE_URL);
        List<String> exclusionUrlList =
            Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(anonUrlSet);
        ignoreUrlSet.addAll(exclusionUrlList);
        log.info("多租户拦截器忽略得请求路径有:{}",ignoreUrlSet);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        boolean isIgnoreRequest = UrlIgnoreUtils.isIgnoreUrl(ignoreUrlSet, servletPath);
        log.info("本次请求多租户拦截器是否拦截:{},请求路径:{}",!isIgnoreRequest,servletPath);
        TenantHolder.setIgnoredRequest(isIgnoreRequest);
        return true;
    }

}
