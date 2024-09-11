package ai.remi.comm.safe.annotation;

import ai.remi.comm.safe.constant.SafeConstants;
import ai.remi.comm.safe.enums.LimitType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 基于Redis的防刷限流注解
 *
 * @author DianJiu 【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-19
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RedisLimit {
    /**
     * key
     */
    String key() default SafeConstants.REDIS_LIMIT_KEY;

    /**
     * Key的前缀
     */
    String prefix() default "";

    /**
     * 一定时间内最多访问次数
     */
    int count();

    /**
     * 给定的时间范围 单位(秒)
     */
    int period();

    /**
     * 限流的类型(用户自定义key或者请求ip)
     */
    LimitType limitType() default LimitType.CUSTOMER;
}
