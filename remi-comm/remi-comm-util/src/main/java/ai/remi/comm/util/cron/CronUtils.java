package ai.remi.comm.util.cron;

import org.springframework.scheduling.support.CronExpression;

/**
 * Quartz Cron 表达式的工具类
 */
public class CronUtils {
    /**
     * 校验 CRON 表达式是否有效
     *
     * @param cronExpression CRON 表达式
     * @return 是否有效
     */
    public static boolean isValid(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }
}
