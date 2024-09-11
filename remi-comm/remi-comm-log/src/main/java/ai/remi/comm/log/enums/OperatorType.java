package ai.remi.comm.log.enums;

import ai.remi.comm.core.enums.TypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作人类别
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OperatorType implements TypeEnum {

    /**
     * 后台用户
     */
    MANAGE(1, "管理端用户"),

    /**
     * 手机端用户
     */
    MOBILE(2, "移动端用户"),

    /**
     * 其它
     */
    OTHER(99, "其它");

    private Integer code;

    private String desc;

}
