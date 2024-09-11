package ai.remi.comm.log.enums;

import ai.remi.comm.core.enums.TypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务操作类型
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BusinessType implements TypeEnum {

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 修改
     */
    UPDATE(2, "修改"),

    /**
     * 删除
     */
    DELETE(3, "删除"),

    /**
     * 导入
     */
    IMPORT(4, "导入"),

    /**
     * 导出
     */
    EXPORT(5, "导出"),

    /**
     * 下载
     */
    DOWNLOAD(6, "下载"),

    /**
     * 上传
     */
    UPLOAD(7, "上传"),

    /**
     * 授权
     */
    GRANT(8, "授权"),

    /**
     * 登录
     */
    LOGIN(9, "登录"),

    /**
     * 登出
     */
    LOGOUT(10, "登出"),

    /**
     * 强退
     */
    FORCE(11, "强退"),

    /**
     * 修改状态
     */
    STATUS(12, "修改状态"),

    /**
     * 生成代码
     */
    GENCODE(13, "生成代码"),

    /**
     * 清空数据
     */
    CLEAN(14, "清空数据"),

    /**
     * 更新缓存
     */
    REFRESH(15, "更新缓存"),

    /**
     * 其它
     */
    OTHER(99, "其它");

    private Integer code;

    private String desc;
}
