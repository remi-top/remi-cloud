package ai.remi.comm.web.filter;


import com.alibaba.fastjson.JSON;
import ai.remi.comm.core.config.AuthFilterConfig;
import ai.remi.comm.core.constant.FilterIgnoreConstant;
import ai.remi.comm.core.constant.HeaderConstant;
import ai.remi.comm.core.enums.ServiceTypeEnum;
import ai.remi.comm.util.auth.AuthInfo;
import ai.remi.comm.util.auth.RequestHolder;
import ai.remi.comm.util.url.UrlIgnoreUtils;
import ai.remi.comm.web.auth.AuthHandler;
import ai.remi.comm.web.auth.AuthHandlerFactory;
import ai.remi.comm.web.excel.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;


/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 全局验证过滤器
 */
@Slf4j
public class AuthFilter implements Filter {


    /**
     * 获取验证信息 并存储到 ThreadLocal 中,可通过 {@link RequestHolder#getAuthInfo()}  } 获取验证信息
     *
     * @param servletRequest  请求数据
     * @param servletResponse 响应数据
     * @param filterChain     过滤器链
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();

//        String applicationName = SpringUtil.getApplicationName();
        String applicationName = SpringContextUtil.getApplicationContext().getApplicationName();
        boolean ignoreService = FilterIgnoreConstant.AUTH_FILTER_IGNORE_SERVICE_NAME.contains(applicationName);
        if (ignoreService) {
            filterChain.doFilter(request, servletResponse);
            return;
        }

        AuthFilterConfig authFilterConfig = SpringContextUtil.getBean(AuthFilterConfig.class);
//        AuthFilterConfig authFilterConfig = SpringUtil.getBean(AuthFilterConfig.class);
        Set<String> ignoreUrl = FilterIgnoreConstant.FILTER_COMMON_IGNORE_URL;
        List<String> commonIgnoreUrl = authFilterConfig.getCommonIgnoreUrl();
        List<String> customIgnoreUrl = authFilterConfig.getCustomIgnoreUrl();
        ignoreUrl.addAll(commonIgnoreUrl);
        ignoreUrl.addAll(customIgnoreUrl);
        boolean ignoredUrl = UrlIgnoreUtils.isIgnoreUrl(ignoreUrl, servletPath);
        if (ignoredUrl) {
            filterChain.doFilter(request, servletResponse);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String serviceType = request.getHeader(HeaderConstant.X_SERVICE_TYPE);
        log.info("X_SERVICE_TYPE:{}", serviceType);
        if (StringUtils.isEmpty(serviceType)){
            serviceType = ServiceTypeEnum.WEB_SERVICE.getCode();
        }
        AuthHandler authHandler = AuthHandlerFactory.getAuthHandler(ServiceTypeEnum.codeOf(serviceType));
        AuthInfo authInfo = authHandler.getAuthInfo(request, response);
        log.debug("当前请求路径:{},当前请求认证信息:{}", servletPath, JSON.toJSONString(authInfo));
        RequestHolder.add(authInfo);
        RequestHolder.add(request);
        filterChain.doFilter(request, response);
    }
}
