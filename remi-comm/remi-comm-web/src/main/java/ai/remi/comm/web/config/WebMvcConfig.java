package ai.remi.comm.web.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ai.remi.comm.core.constant.Constants;
import ai.remi.comm.exception.custom.BusinessException;
import ai.remi.comm.web.filter.AuthFilter;
import ai.remi.comm.web.interceptor.HttpInterceptor;
import ai.remi.comm.web.interceptor.TenantInterceptor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc WebMvcConfig
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    /**
     * 防抖过滤器
     */
    //@Autowired
    
    //private ResubmitFilter resubmitFilter;
    
    /**
     * 租户拦截器
     */
    @Resource
    private TenantInterceptor tenantInterceptor;
    
    /**
     * Http拦截器
     */
    @Resource
    private HttpInterceptor httpInterceptor;
    
    /**
     * 防抖拦截器
     */
    //@Autowired
    //private ResubmitInterceptor resubmitInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor).addPathPatterns("/**").order(Integer.MIN_VALUE);
        registry.addInterceptor(httpInterceptor).addPathPatterns("/**").order(Integer.MAX_VALUE);
        //registry.addInterceptor(resubmitInterceptor).addPathPatterns("/**").order(Integer.MAX_VALUE);
    }
    
    /**
     * 解决跨域请求 方式一：
     */
    //@Override
    //public void addCorsMappings(CorsRegistry registry) {
    //    //项目中的所有接口都支持跨域
    //    registry.addMapping("/**")
    //            //是否支持跨域Cookie
    //            .allowCredentials(true)
    //            //配置访问的地址，*代表所有的都可以访问
    //            //.allowedOrigins("http://localhost:8080")
    //            .allowedOriginPatterns(CorsConfiguration.ALL)
    //            //允许的请求头参数
    //            .allowedHeaders(CorsConfiguration.ALL)
    //            //允许的请求方式 "GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"
    //            .allowedMethods(CorsConfiguration.ALL)
    //            // 跨域允许时间
    //            .maxAge(3600);
    //
    //    WebMvcConfigurer.super.addCorsMappings(registry);
    //}
    
    /**
     * 解决跨域请求 方式二：可指定拦截器执行顺序
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        //是否支持跨域Cookie
        corsConfig.setAllowCredentials(true);
        //配置访问的地址，*代表所有的都可以访问
        //corsConfig.addAllowedOrigin("http://localhost:8080");
        corsConfig.addAllowedOriginPattern(CorsConfiguration.ALL);
        //允许的请求头参数
        corsConfig.addAllowedHeader(CorsConfiguration.ALL);
        //允许的请求头参数
        corsConfig.addAllowedMethod(CorsConfiguration.ALL);
        //默认可不设置这个暴露的头。这个为了安全问题，不能使用*。设置成*，后面会报错：throw new IllegalArgumentException("'*' is not a valid exposed header value");
        //corsConfig.addExposedHeader("");
        corsConfig.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfig);

        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(configSource));
        corsBean.setName("crossOriginFilter");
        //这个顺序也有可能会有影响，尽量设置在拦截器前面
        corsBean.setOrder(0);
        return corsBean;
    }

    /**
     * 自定义全局序列化方式
     */
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = httpMessageConverter.getObjectMapper();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleModule simpleModule = new SimpleModule();
        //序列化日期格式
        simpleModule.addSerializer(Date.class, new DateSerializer(false, simpleDateFormat));
        //Long序列化为String
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        //字符串转String
        simpleModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                String date = jsonParser.getText();
                try {
                    return simpleDateFormat.parse(date);
                } catch (ParseException e) {
                    throw new BusinessException(Constants.ERROR, e.getMessage());
                }
            }
        });
        //反序列化日期格式
        objectMapper.registerModule(simpleModule);
        //序列化多余字段不报错
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //序列化地区
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        //如果值为null时字段key还是否输出:ALWAYS(总是输出),NON_NULL(为null的不输出)
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
                String fieldName = gen.getOutputContext().getCurrentName();
                try {
                    //反射获取字段类型
                    Field field = gen.getCurrentValue().getClass().getDeclaredField(fieldName);
                    if (Objects.equals(field.getType(), String.class)) {
                        //字符串型空值""
                        gen.writeString("");
                        return;
                    } else if (Objects.equals(field.getType(), List.class)) {
                        //列表型空值返回[]
                        gen.writeStartArray();
                        gen.writeEndArray();
                        return;
                    } else if (Objects.equals(field.getType(), Map.class)) {
                        //map型空值返回{}
                        gen.writeStartObject();
                        gen.writeEndObject();
                        return;
                    }
                } catch (NoSuchFieldException e) {
                }
                //默认返回""
                gen.writeString("");
            }
        });
        return new HttpMessageConverters(httpMessageConverter);
    }

    /*@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(resubmitFilter);
        //过滤所有路径
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("resubmitFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }*/

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        AuthFilter authFilter = new AuthFilter();
        FilterRegistrationBean<AuthFilter> authFilterBean = new FilterRegistrationBean<>(authFilter);
        //过滤所有路径
        authFilterBean.addUrlPatterns("/*");
        authFilterBean.setName("authFilter");
        authFilterBean.setOrder(3);
        return authFilterBean;
    }
}