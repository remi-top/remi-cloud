package ai.remi.comm.core.constant;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 通用常量信息
 */
public class Constants {
    
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
    
    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";
    
    /**
     * RMI 远程方法调用
     */
    public static final String LOOKUP_RMI = "rmi:";
    
    /**
     * LDAP 远程方法调用
     */
    public static final String LOOKUP_LDAP = "ldap:";
    
    /**
     * LDAPS 远程方法调用
     */
    public static final String LOOKUP_LDAPS = "ldaps:";
    
    /**
     * http请求
     */
    public static final String HTTP = "http://";
    
    /**
     * https请求
     */
    public static final String HTTPS = "https://";
    
    /**
     * 成功标记
     */
    public static final String SUCCESS = "000000";
    
    /**
     * 失败标记
     */
    public static final String ERROR = "111111";
    
    /**
     * 登录成功状态
     */
    public static final String LOGIN_SUCCESS_STATUS = "0";
    
    /**
     * 登录失败状态
     */
    public static final String LOGIN_FAIL_STATUS = "1";
    
    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";
    
    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";
    
    /**
     * 注册
     */
    public static final String REGISTER = "Register";
    
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";
    
    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";
    
    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";
    
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";
    
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";
    
    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 2;
    
    
    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";
    
    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";
    
    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
    
    /**
     * 定时任务白名单配置（仅允许访问的包名，如其他需要可以自行添加）
     */
    public static final String[] JOB_WHITELIST_STR = {"ai.remi"};
    
    /**
     * 定时任务违规的字符
     */
    public static final String[] JOB_ERROR_STR = {"java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
            "org.springframework", "org.apache", "ai.remi.comm.core.utils.file"};

    /**
     * 系统市公司编号
     */
    public static final String SYS_COMPANY_ID = "1667075454562709506";

    /**
     * 登录无权限
     */
    public static final String LOGIN_NO_PERMISSION = "401";

    /**
     * 无权限访问资源
     */
    public static final String CODE_401 = "401";

    /**
     * 当前角色权限不足
     */
    public static final String CODE_403 = "403";

    /**
     * SRS校验数据错误码
     */
    public static final String SRS_DATA_CHECK_ERROR = "A02001";

    /**
     * 功能检验错误码
     */
    public static final String COMM_CHECK_ERROR = "222222";
}
