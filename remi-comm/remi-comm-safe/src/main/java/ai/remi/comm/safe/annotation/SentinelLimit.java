package ai.remi.comm.safe.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 基于Sentinel的防刷限流注解
 *
 * @author DianJiu 【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-19
 */
@Inherited
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface SentinelLimit {

    String resourceName();

    int limitCount() default 5;

}
