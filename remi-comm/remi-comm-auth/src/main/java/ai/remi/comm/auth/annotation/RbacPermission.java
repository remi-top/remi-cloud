package ai.remi.comm.auth.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @author DianJiu 【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-19
 * @desc 权限校验
 */
@Inherited
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RbacPermission {

    /**
     * 角色
     *
     * @return
     */
    String roles() default "";

    /**
     * 权限
     *
     * @return
     */
    String permissions() default "";
}
