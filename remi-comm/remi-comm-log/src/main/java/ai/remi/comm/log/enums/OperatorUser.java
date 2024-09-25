package ai.remi.comm.log.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Redis key枚举类
 *
 * @author dianjiu
 * @date 2021-12-7 16:10:35
 */
@Getter
@AllArgsConstructor
public enum OperatorUser {
    /**
     * 客户登录token,24小时失效 1:{} = customerId 2:{} = token 使用方式 RedisKeysEnum.CUSTOMER_LOGIN_TOKEN.getKey(customerId,token)
     */
    APP_CODE("remi-saas-iam:app-code:{}:{}"),
    USER_INFO("remi-saas-iam:user-info:{}");


    /**
     * redis key
     */
    private String key;

    /**
     * key失效时间，单位秒
     */
    //private Long expireAt;
    public String getKey(String... key) {
        return StrUtil.format(this.key, key);
    }
}