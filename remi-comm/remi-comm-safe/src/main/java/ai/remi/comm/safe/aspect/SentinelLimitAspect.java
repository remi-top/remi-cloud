package ai.remi.comm.safe.aspect;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import ai.remi.comm.safe.annotation.SentinelLimit;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
@Order(10)
public class SentinelLimitAspect {
    private static void initFlowRule(String resourceName,int limitCount) {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //设置受保护的资源
        rule.setResource(resourceName);
        //设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源阈值
        rule.setCount(limitCount);
        rules.add(rule);
        //加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    @Pointcut(value = "@annotation(ai.remi.comm.safe.annotation.SentinelLimit)")
    public void rateLimit() {

    }

    @Around("rateLimit()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //1、获取当前的调用方法
        Method currentMethod = getCurrentMethod(joinPoint);
        if (Objects.isNull(currentMethod)) {
            return null;
        }
        //2、从方法注解定义上获取限流的类型
        String resourceName = currentMethod.getAnnotation(SentinelLimit.class).resourceName();
        if(StringUtils.isEmpty(resourceName)){
            throw new RuntimeException("资源名称为空");
        }
        int limitCount = currentMethod.getAnnotation(SentinelLimit.class).limitCount();
        initFlowRule(resourceName,limitCount);

        Entry entry = null;
        Object result = null;
        try {
            entry = SphU.entry(resourceName);
            try {
                result = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } catch (BlockException ex) {
            // 资源访问阻止，被限流或被降级
            // 在此处进行相应的处理操作
            System.out.println("blocked");
            return "被限流了";
        } catch (Exception e) {
            Tracer.traceEntry(e, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return result;
    }

    private Method getCurrentMethod(JoinPoint joinPoint) {
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method target = null;
        for (Method method : methods) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                target = method;
                break;
            }
        }
        return target;
    }
}
