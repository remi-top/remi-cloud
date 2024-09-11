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

    public static void main(String[] args) {
        String validCron = "0 0 12 * * ?";
        String invalidCron = "invalid";

        System.out.println("Valid Cron: " + isValid(validCron)); // 输出 true
        System.out.println("Invalid Cron: " + isValid(invalidCron)); // 输出 false
    }
}
