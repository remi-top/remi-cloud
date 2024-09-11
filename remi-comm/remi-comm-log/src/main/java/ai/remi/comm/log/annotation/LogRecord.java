package ai.remi.comm.log.annotation;


import ai.remi.comm.log.enums.BusinessType;
import ai.remi.comm.log.enums.OperatorType;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @author DianJiu 【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-19
 * @desc 操作日志记录
 */
@Inherited
@Documented
@Order(Integer.MIN_VALUE)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecord {

    /**
     * 接口名称
     */
    String content() default "";

    /**
     * 功能操作类型
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;

    /**
     * 是否保存异常的信息
     */
    boolean isSaveErrorInfo() default true;

    /**
     * 在方法成功执行后打点，记录方法的执行时间发送到指标系统，默认关闭
     *
     * @return
     */
    boolean recordSuccessMetrics() default false;

    /**
     * 在方法成功失败后打点，记录方法的执行时间发送到指标系统，默认关闭
     */
    boolean recordFailMetrics() default false;

    /**
     * 出现异常后忽略异常返回默认值，默认关闭
     */
    boolean ignoreException() default false;

    /**
     * 排除指定的请求参数
     */
    public String[] excludeParamNames() default {};
}
