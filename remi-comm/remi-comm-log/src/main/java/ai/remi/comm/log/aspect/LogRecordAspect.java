package ai.remi.comm.log.aspect;

import ai.remi.comm.core.spring.SpringProperties;
import ai.remi.comm.log.annotation.LogRecord;
import ai.remi.comm.log.domain.OperateLog;
import ai.remi.comm.log.enums.BusinessStatus;
import ai.remi.comm.log.enums.OperatorUser;
import ai.remi.comm.log.filter.PropertyPreExcludeFilter;
import ai.remi.comm.log.service.AsyncLogService;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.ip.IpAddrUtils;
import ai.remi.comm.util.ip.IpInfoUtils;
import ai.remi.comm.util.string.StringUtils;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 操作日志记录处理
 */
@Slf4j
@Aspect
public class LogRecordAspect {
    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    private final ThreadLocal<Map<String, Object>> INFO_THREADLOCAL = new ThreadLocal<Map<String, Object>>();

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<>("Cost Time");

    @Resource
    private AsyncLogService asyncLogService;

    @Resource
    private RedisService redisService;

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(logRecord)")
    public void boBefore(JoinPoint joinPoint, LogRecord logRecord) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
        //登出接口需要前置储存用户信息
        handleLog(joinPoint, logRecord);
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(logRecord)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, LogRecord logRecord, Object jsonResult) {
        handleLog(joinPoint, logRecord, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(logRecord)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, LogRecord logRecord, Exception e) {
        handleLog(joinPoint, logRecord, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, LogRecord logRecord) {
        // 传递线程信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(requestAttributes, true);
        HttpServletRequest request = requestAttributes.getRequest();
        // 记录请求内容，threadInfo存储所有内容
        Map<String, Object> threadInfo = new HashMap<>();
        //该方法用于获取客户端发出请求时的完整URL，包括协议、服务器名、端口号、资源路径等信息，但不包括后面的查询参数部分。
        // 注意，getRequestRUL()方法返回的时StringBuffer类型，而不是String类型。
        threadInfo.put("url", request.getRequestURL());
        //该方法用于获取请求行中资源名称部分，即位于URL的主机和端口之后、参数部分之前的部分
        threadInfo.put("uri", request.getRequestURI());
        //该方法用于获取HTTP请求消息中的请求方式（如GET、POST等）
        threadInfo.put("httpMethod", request.getMethod());
        //该方法用于获取请求的协议名，例如http、https或ftp
        threadInfo.put("scheme", request.getScheme());
        //该方法用于获取请求客户端的IP地址，其格式类似于"192.168.0.1"
        //threadInfo.put("ip", request.getRemoteAddr());
        threadInfo.put("ip", IpAddrUtils.getIpAddr(request));
        //检查是否为内网IP
        String ipAddress = IpAddrUtils.internalIp(IpAddrUtils.getIpAddr(request)) ? "内网IP" : IpInfoUtils.getInfo(IpAddrUtils.getIpAddr(request)).getProvince() + "-" + IpInfoUtils.getInfo(IpAddrUtils.getIpAddr(request)).getCity();
        threadInfo.put("ipAddress", ipAddress);
        //该方法用于获取Content-Type头字段的值，结果为String类型
        threadInfo.put("contentType", request.getContentType());
        //包含了一个特征字符串，用来让网络协议的对端来识别发起请求的用户代理软件的应用类型、操作系统、软件开发商以及版本号。
        //UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        threadInfo.put("platform", userAgent.getOperatingSystem().getName());
        threadInfo.put("browser", userAgent.getBrowser().getName());
        //从请求头获取操作人
        threadInfo.put("operator", request.getHeader("X-Unique-Id"));
        //从请求头获取部门租户
        threadInfo.put("deptTenantId", request.getHeader("X-Dept-Tenant-Id"));
        //从请求头获取公司租户
        threadInfo.put("companyTenantId", request.getHeader("X-Company-Tenant-Id"));
        //从请求头获取操作人
        String accessToken = request.getHeader("X-Access-Token");
        threadInfo.put("token", accessToken);
        threadInfo.put("userId", redisService.hget(OperatorUser.USER_INFO.getKey(accessToken), "id"));
        threadInfo.put("userCode", redisService.hget(OperatorUser.USER_INFO.getKey(accessToken), "userCode"));
        threadInfo.put("userName", redisService.hget(OperatorUser.USER_INFO.getKey(accessToken), "userName"));
        threadInfo.put("userNameEn", redisService.hget(OperatorUser.USER_INFO.getKey(accessToken), "userNameEn"));
        //从请求头获取系统语言
        threadInfo.put("language", request.getHeader("X-USER-LANGUAGE"));
        //从请求头获取业务流水号
        threadInfo.put("businessNo", request.getHeader("X-Business-No"));
        INFO_THREADLOCAL.set(threadInfo);
    }

    protected void handleLog(final JoinPoint joinPoint, LogRecord logRecord, final Exception e, Object jsonResult) {
        try {
            // *========解析请求信息=========*//
            OperateLog operateLog = new OperateLog();

            // *========获取注解信息=========*//
            //获取方法签名
            //MethodSignature signature = getSignature(joinPoint);
            //获取注解方法
            Method method = getMethod(joinPoint);
            //获取注解对象
            //LogRecord logRecord = getAnnotation(joinPoint, LogRecord.class);
            // 获取连接点所在类名
            String classname = joinPoint.getTarget().getClass().getSimpleName();
            // 获取连接点所在方法名称
            String methodName = method.getName();
            String name = String.format("%s.%s()", classname, methodName);
            // 保存请求方法
            operateLog.setRequestMethod(name);

            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, logRecord, operateLog, jsonResult);

            // 是否需要保存request，参数和值
            if (logRecord.isSaveRequestData()) {
                // 获取参数的信息，传入到数据库中
                setRequestValue(joinPoint, operateLog, logRecord.excludeParamNames());
            }

            // 是否需要保存response，参数和值
            if (logRecord.isSaveResponseData() && ObjectUtil.isNotNull(jsonResult)) {
                String result = JSON.toJSONString(jsonResult);
                // 对登录日志的特殊处理
                if (operateLog.getBusinessType()== 9) {
                    Object data = JSON.parseObject(result).get("data");
                    String accessToken = String.valueOf(JSON.parseObject(JSON.toJSONString(data)).get("accessToken"));
                    setOperateUser(accessToken, operateLog);
                }
                operateLog.setStatus(String.valueOf(JSON.parseObject(result).get("code")));
                operateLog.setResponseBody(result);
            }

            //请求的成功或失败
            if (e != null) {
                operateLog.setStatus(BusinessStatus.FAIL.getCode());
                // 是否需要保存errorMsg，参数和值
                if (logRecord.isSaveErrorInfo()) {
                    operateLog.setStatus("111111");
                    operateLog.setErrorInfo(StrUtil.sub(e.getMessage(), 0, 5000));
                }
            }

            // 保存数据库
            asyncLogService.saveOperateLog(operateLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
            INFO_THREADLOCAL.remove();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param logRecord  日志切面
     * @param operateLog 操作日志
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, LogRecord logRecord, OperateLog operateLog, Object jsonResult) throws Exception {
        Map<String, Object> objectMap = INFO_THREADLOCAL.get();
        // 业务模块
        operateLog.setAppId(SpringProperties.getString("log.record.appId"));
        operateLog.setAppCode(SpringProperties.getString("log.record.appCode"));
        // 操作方法名称或描述
        operateLog.setContent(logRecord.content());
        // 业务操作类型
        operateLog.setBusinessType(logRecord.businessType().getCode());
        // 操作人类别
        operateLog.setOperatorType(logRecord.operatorType().getCode());
        // 操作人IP
        operateLog.setUserIp(String.valueOf(objectMap.get("ip")));
        operateLog.setIpAddress(String.valueOf(objectMap.get("ipAddress")));
        // 操作人Id
        //operateLog.setUserId(Objects.nonNull(objectMap.get("operator")) ? String.valueOf(objectMap.get("operator")) : null);
        // 操作人信息
        operateLog.setUserId(Objects.nonNull(objectMap.get("userId")) ? String.valueOf(objectMap.get("userId")) : null);
        operateLog.setUserCode(Objects.nonNull(objectMap.get("userCode")) ? String.valueOf(objectMap.get("userCode")) : null);
        operateLog.setUserName(Objects.nonNull(objectMap.get("userName")) ? String.valueOf(objectMap.get("userName")) : null);
        operateLog.setUserNameEn(Objects.nonNull(objectMap.get("userNameEn")) ? String.valueOf(objectMap.get("userNameEn")) : null);
        // 用户系统语言
        operateLog.setUserLanguage(Objects.nonNull(objectMap.get("language")) ? String.valueOf(objectMap.get("language")) : null);
        // 请求地址
        operateLog.setRequestUrl(String.valueOf(objectMap.get("url")));
        // 请求类型
        operateLog.setRequestType(String.valueOf(objectMap.get("httpMethod")));
        // 请求报文类型
        operateLog.setContentType(Objects.nonNull(objectMap.get("contentType")) ? String.valueOf(objectMap.get("contentType")) : null);
        // 业务流水号
        operateLog.setBusinessNo(Objects.nonNull(objectMap.get("businessNo")) ? String.valueOf(objectMap.get("businessNo")) : null);
        // 链路追踪ID
        //logRecordDTO.setTraceId();
        // 浏览器、操作系统、UI引擎标识
        operateLog.setPlatform(String.valueOf(objectMap.get("platform")));
        operateLog.setBrowser(String.valueOf(objectMap.get("browser")));
        // 部门租户
        operateLog.setDeptTenantId(String.valueOf(objectMap.get("deptTenantId")));
        // 公司租户
        operateLog.setCompanyTenantId(String.valueOf(objectMap.get("companyTenantId")));
        // 设置消耗时间
        operateLog.setRequestTime(LocalDateTime.ofEpochSecond(TIME_THREADLOCAL.get() / 1000, 0, ZoneOffset.ofHours(8)));
        operateLog.setResponseTime(LocalDateTime.ofEpochSecond(System.currentTimeMillis() / 1000, 0, ZoneOffset.ofHours(8)));
        operateLog.setExecuteTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
    }

    /**
     * 保存操作人信息
     *
     * @param accessToken
     * @param operateLog
     */
    private void setOperateUser(String accessToken, OperateLog operateLog) {
        if (StringUtils.isNotBlank(accessToken)) {
            String id = String.valueOf(redisService.hget(OperatorUser.USER_INFO.getKey(accessToken), "id"));
            operateLog.setUserId(id);
            String userCode = String.valueOf(redisService.hget(OperatorUser.USER_INFO.getKey(accessToken), "userCode"));
            operateLog.setUserCode(userCode);
            String userName = String.valueOf(redisService.hget(OperatorUser.USER_INFO.getKey(accessToken), "userName"));
            operateLog.setUserName(userName);
            String userNameEn = String.valueOf(redisService.hget(OperatorUser.USER_INFO.getKey(accessToken), "userNameEn"));
            operateLog.setUserNameEn(userNameEn);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operateLog        操作日志
     * @param excludeParamNames 排除指定的请求参数
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, OperateLog operateLog, String[] excludeParamNames) throws Exception {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        // 获取请求类型
        // String requestType = operateLog.getRequestType();
        String requestType = request.getMethod();
        // 获取请求参数
        Map<String, String> paramsMap = converMap(request.getParameterMap());
        // 组装请求参数
        if (MapUtils.isEmpty(paramsMap) && (HttpMethod.PUT.name().equals(requestType) || HttpMethod.POST.name().equals(requestType))) {
            String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
            operateLog.setRequestBody(params);
        } else {
            operateLog.setRequestBody(JSON.toJSONString(paramsMap, excludePropertyPreFilter(excludeParamNames)));
        }
    }

    /**
     * 请求参数 转换
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> returnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            returnMap.put(key, paramMap.get(key)[0]);
        }
        return returnMap;
    }


    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
        StringBuilder params = new StringBuilder();
        if (ArrayUtil.isNotEmpty(paramsArray)) {
            for (Object o : paramsArray) {
                if (ObjectUtil.isNotNull(o) && !isFilterObject(o)) {
                    try {
                        String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
                        params.append(jsonObj).append(StrUtil.SPACE);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 忽略敏感属性
     */
    public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames) {
        return new PropertyPreExcludeFilter().addExcludes(ArrayUtil.addAll(EXCLUDE_PROPERTIES, excludeParamNames));
    }

    /**
     * 判断是否需要过滤的对象
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
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
