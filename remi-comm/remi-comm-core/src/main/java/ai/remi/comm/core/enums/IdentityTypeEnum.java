package ai.remi.comm.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 身份类型枚举定义
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum IdentityTypeEnum {

    STARTDIS("remi", "瑞米官方账号"),
    COMPANY("company", "集团公司账户"),
    VISITOR("visitor", "游客体验账号");

    private String code;

    private String desc;
}
