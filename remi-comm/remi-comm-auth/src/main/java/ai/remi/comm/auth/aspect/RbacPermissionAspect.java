package ai.remi.comm.auth.aspect;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import ai.remi.comm.auth.annotation.RbacPermission;
import ai.remi.comm.core.constant.Constants;
import ai.remi.comm.core.constant.HeaderConstant;
import ai.remi.comm.exception.custom.BusinessException;
import ai.remi.comm.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author DianJiu 【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-19
 * @desc
 */
@Aspect
@Component
@Order(10)
@Slf4j
public class RbacPermissionAspect {
    private static final Logger logger = LoggerFactory.getLogger(RbacPermissionAspect.class);

    private final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    private final String ROLE_MENU = "tdcd-cloud-mps:role-menu:{}";

    @Resource
    private RedisService redisService;

    @Pointcut("@annotation(ai.remi.comm.auth.annotation.RbacPermission)")
    public void rbacPermissionPointCut() {
    }

    /**
     * 环绕通知
     * 方式一：传入注解，获取注解中的参数
     *
     * @Around("logRecordPointCut()&&@annotation(logRecord)") public void doAround(ProceedingJoinPoint joinPoint, LogRecord logRecord) {}
     * 方式二：通过切点获取注解实体
     */
    @Around("rbacPermissionPointCut()")
    //@Around("rbacPermissionPointCut()&&@annotation(RbacPermission)")
    //public void doAround(ProceedingJoinPoint joinPoint, RbacPermission rbacPermission) {
    public Object doAround(ProceedingJoinPoint joinPoint) {
        // 接收到请求
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 记录请求内容，threadInfo存储所有内容
        Map<String, Object> threadInfo = new HashMap<>();
        //该方法用于获取客户端发出请求时的完整URL，包括协议、服务器名、端口号、资源路径等信息，但不包括后面的查询参数部分。
        // 注意，getRequestRUL()方法返回的时StringBuffer类型，而不是String类型。
        threadInfo.put("url", request.getRequestURL());
        //该方法用于获取请求行中资源名称部分，即位于URL的主机和端口之后、参数部分之前的部分
        threadInfo.put("uri", request.getRequestURI());
        //该方法用于获取请求的协议名，例如http、https或ftp
        threadInfo.put("scheme", request.getScheme());
        //该方法用于获取HTTP请求消息中的请求方式（如GET、POST等）
        threadInfo.put("method", request.getMethod());
        logger.info("请求方式：{}", threadInfo.get("method"));
        //该方法用于获取请求客户端的IP地址，其格式类似于"192.168.0.1"
        threadInfo.put("ip", request.getRemoteAddr());
        logger.info("请求ip：{}", threadInfo.get("ip"));
        //该方法用于获取Content-Type头字段的值，结果为String类型
        threadInfo.put("contentType", request.getContentType());
        //包含了一个特征字符串，用来让网络协议的对端来识别发起请求的用户代理软件的应用类型、操作系统、软件开发商以及版本号。
        threadInfo.put("userAgent", request.getHeader("User-Agent"));
        //threadInfo.put("businessNo", request.getHeader("businessNo"));
        threadLocal.set(threadInfo);
        //获取方法签名
        MethodSignature signature = getSignature(joinPoint);
        //获取注解对象
        RbacPermission rbacPermission = getAnnotation(joinPoint, RbacPermission.class);
        //获取注解方法
        Method method = getMethod(joinPoint);
        // 获取连接点所在类名
        String classname = joinPoint.getTarget().getClass().getSimpleName();
        // 获取连接点所在方法名称
        String methodName = method.getName();
        String functionName = String.format("【%s.%s()】", classname, methodName);
        threadInfo.put("functionName", functionName);
        logger.info("请求类方法：{}", threadInfo.get("functionName"));
        // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
        // 参数名
        String[] argNames = signature.getParameterNames();
        // 参数值
        Object[] args = joinPoint.getArgs();
        StringBuilder functionArgs = new StringBuilder();
        //不为空时便利组装
        if (Objects.nonNull(argNames) && argNames.length > 0 && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                functionArgs.append(argNames[i] + ":" + args[i] + ";");
            }
        }
        threadInfo.put("functionArgs", functionArgs.toString());
        logger.info("请求参数：{}", threadInfo.get("functionArgs"));

//        threadInfo.put("accessToken", request.getHeader("accessToken"));
        threadInfo.put("accessToken", request.getHeader(HeaderConstant.X_ACCESS_TOKEN));
        logger.info("鉴权令牌：{}", threadInfo.get("accessToken"));

        // 权限校验
        if (method.isAnnotationPresent(RbacPermission.class)) {
            String roles = rbacPermission.roles();
            String permissions = rbacPermission.permissions();

            // 根据鉴权令牌去redis查询到用户信息、角色信息、权限信息
            //Map map = redisService.hmget(RedisDemoEnum.ACCESS_TOKEN.getKey(String.valueOf(threadInfo.get("accessToken"))));
            Map map = redisService.hmget(String.valueOf(threadInfo.get("accessToken")));
            if (null == map || map.size() == 0) {
                throw new BusinessException(Constants.LOGIN_NO_PERMISSION, "accessToken expire! please login again!");
            }
            String roleCode = String.valueOf(map.get("roleCode"));
            List<String> roleCodes = Arrays.asList(roleCode.split(","));
            // 校验角色
            if (StringUtils.isNotBlank(roles)) {
                // 校验用户角色
//                JSONObject jsonObject = JSONObject.parseObject(String.valueOf(map.get("user")));
//                if (!Arrays.asList(roles.split(",")).contains(jsonObject.get("roleCode"))) {
//                    throw new BusinessException(Constants.CODE_403, "当前角色权限不足");
//                }
                for (String authRoleCode : Arrays.asList(roles.split(","))) {
                    if (!roleCodes.contains(authRoleCode)){
                        throw new BusinessException(Constants.CODE_403, "the role no Permission!");
                    }else {
                        break;
                    }
                }
//                if (!Arrays.asList(roles.split(",")).contains(roleCode)) {
//                    throw new BusinessException(Constants.CODE_403, "the role no Permission!");
//                }
            }
            // 校验权限
            if (StringUtils.isNotBlank(permissions)) {
                // 组装用户权限
                StringBuilder userPermissions = new StringBuilder();

//                String menuString = JSONObject.toJSONString(map.get("menus"));
//                List<JSONObject> menus = JSONObject.parseArray(menuString);
                List<JSONObject> menus = new ArrayList<>();
                for (String code : roleCodes) {
                    String data = null;
                    try {
                        data = redisService.getString(StrUtil.format(ROLE_MENU, code));
                    } catch (Exception e) {
                        log.error("获取用户权限异常:{}", e.getMessage());
                        throw new BusinessException(Constants.CODE_403, "get user Permission error!");
                    }
                    List<JSONObject> menuList = JSONObject.parseArray(data, JSONObject.class);
                    menus.addAll(menuList);
                }

                for (int i = 0; i < menus.size(); i++) {
                    if ("2".equals(menus.get(i).get("menuType"))) {
                        userPermissions.append(menus.get(i).get("menuPermission"));
                        if (i != menus.size() - 1) {
                            userPermissions.append(",");
                        }
                    }
                }
                // 校验用户权限
                if (!Arrays.asList(userPermissions.toString().split(",")).containsAll(Arrays.asList(permissions.split(",")))) {
                    throw new BusinessException(Constants.CODE_403, "the user no Permission");
                }
            }
        }

        //执行到这里开始走进来的方法体（必须声明）
        Object returnValue;
        try {
            returnValue = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new BusinessException(((BusinessException) throwable).getCode(), (throwable).getMessage());
        }
        return returnValue;
    }


    private MethodSignature getSignature(JoinPoint joinPoint) {
        //通过连接点获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature;
    }

    private Method getMethod(JoinPoint joinPoint) {
        //通过连接点获取方法签名
        MethodSignature signature = getSignature(joinPoint);
        //获取注解服务的方法
        Method method = signature.getMethod();
        return method;
    }

    private <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> annotationClass) {
        Method method = getMethod(joinPoint);
        //获取注解类
        T annotation = method.getAnnotation(annotationClass);
        return annotation;
    }

}
