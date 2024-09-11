package ai.remi.comm.feign.aspect;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Remi
 * @email remi@dianjiu.cc
 * @desc Feign 配置注册
 */
@Configuration
public class FeignAutoConfiguration {

    /**
     * 自定义请求拦截器
     * 传递用户信息请求头，防止丢失
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }

    /**
     * 设置Feign客户端日志级别
     * <p>
     * 有四种日志记录级别可供选择：
     * <p>
     * - NONE – 不记录日志，这是默认设置
     * - BASIC – 仅记录请求方法、URL 和响应状态
     * - HEADERS – 将基本信息与请求和响应标头一起记录
     * - FULL – 记录请求和响应的主体、标头和元数据
     */
    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }

    /**
     * Feign 的默认错误处理程序ErrorDecoder.default总是抛出FeignException。
     * 种行为并不总是最有用的。因此，要自定义抛出的异常
     * @return
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new RemiFeignErrorDecoder();
    }
}
