package ai.remi.comm.web.interceptor;

import ai.remi.comm.util.auth.RequestHolder;
import ai.remi.comm.util.auth.TenantHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 主要用于请求结束  清除当前线程相关数据  清除所有 ThreadLocal
 */
@Component
public class HttpInterceptor implements HandlerInterceptor {
    /**
     * This implementation is empty.
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
        TenantHolder.remove();
    }
}
