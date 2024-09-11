package ai.remi.comm.log.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作状态
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BusinessStatus {
    /**
     * 成功
     */
    SUCCESS("000000", "成功"),

    /**
     * 失败
     */
    FAIL("111111", "失败");


    private String code;

    private String desc;

}
