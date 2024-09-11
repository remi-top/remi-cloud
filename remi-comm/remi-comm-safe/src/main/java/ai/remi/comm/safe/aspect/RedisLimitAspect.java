package ai.remi.comm.safe.aspect;


import com.google.common.collect.ImmutableList;
import ai.remi.comm.exception.custom.BusinessException;
import ai.remi.comm.safe.annotation.RedisLimit;
import ai.remi.comm.safe.enums.LimitType;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(10)
public class RedisLimitAspect {

    private static final Logger logger = LoggerFactory.getLogger(RedisLimitAspect.class);
    private final RedisTemplate<String, Serializable> limitRedisTemplate;

    @Resource
    private DefaultRedisScript<Number> redisLuaScript;

    @Autowired
    public RedisLimitAspect(RedisTemplate<String, Serializable> limitRedisTemplate) {
        this.limitRedisTemplate = limitRedisTemplate;
    }


    @Pointcut(value = "@annotation(ai.remi.comm.safe.annotation.RedisLimit)")
    public void redisLimit() {

    }

    @Around("redisLimit()")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取连接点所在类名
        String classname = joinPoint.getTarget().getClass().getSimpleName();
        // 获取连接点所在方法名称
        String methodName = method.getName();
        String functionName = String.format("【%s.%s()】", classname, methodName);

        RedisLimit redisLimit = method.getAnnotation(RedisLimit.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        LimitType limitType = redisLimit.limitType();
        String key = redisLimit.key();
        if (StringUtils.isEmpty(key)) {
            if (limitType == LimitType.IP) {
                key = getIpAddr(request);
            } else {
                key = redisLimit.key();
            }
        }

        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(redisLimit.prefix(), key));

        //执行到这里开始走进来的方法体（必须声明）
        Object returnValue;
        try {
            String luaScript = buildLuaScript();
            redisLuaScript = new DefaultRedisScript<>(luaScript, Number.class);
            //调用lua脚本，获取返回结果，这里即为请求的次数
            Number number = limitRedisTemplate.execute(
                    redisLuaScript,
                    keys,
                    redisLimit.count(),
                    redisLimit.period()
            );
            if (number == null || number.intValue() == 0) {
                throw new BusinessException("111111", "访问频率过快，被限流了");
            }
            logger.info("第{}次访问 [{}] 接口!", number, functionName);
            returnValue = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new BusinessException(((BusinessException) throwable).getCode(), (throwable).getMessage());
        }
        return returnValue;
    }

    /**
     * 限流脚本
     */
    private String buildLuaScript() {
        return "local limit = tonumber(ARGV[1])" +
                "local current = tonumber(redis.call('get', key) or '0')" +
                "if current + 1 > limit then" +
                "   return 0" +
                "else" +
                "   current = redis.call('incr',KEYS[1])" +
                "   redis.call('expire',KEYS[1],ARGV[2])" +
                "   return current" +
                "end;";
    }

    /**
     * 获取请求的IP方法
     *
     * @param request
     * @return
     */
    private static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

}
