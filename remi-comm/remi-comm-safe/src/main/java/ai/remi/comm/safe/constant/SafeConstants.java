package ai.remi.comm.safe.constant;

/**
 * 缓存的key 常量
 */
public class SafeConstants {


    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String REDIS_LIMIT_KEY = "redis_limit:";
}
