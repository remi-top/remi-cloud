package ai.remi.boot.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import ai.remi.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用表Application AppDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_app")
public class App extends BaseEntity {


    /**
     * 应用编码（对内唯一标识）
     */
    private String appCode;

    /**
     * 应用key（对外唯一标识）
     */
    private String appKey;

    /**
     * 应用密钥
     */
    private String appSecret;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用英文名称
     */
    private String appNameEn;

    /**
     * 应用描述
     */
    private String appRemark;

    /**
     * 应用英文描述
     */
    private String appRemarkEn;

    /**
     * 应用图标（如：icon-zuopin）
     */
    private String appIcon;

    /**
     * 应用图标颜色（如：#13C2C2）
     */
    private String appIconColor;

    /**
     * 应用备案号
     */
    private String appIcp;

    /**
     * 是否公共应用（0否 1是）
     */
    private String isCommon;

    /**
     * 是否移动端应用（0否 1是）
     */
    private String isMobile;

    /**
     * 每秒间隔访问次数限制
     */
    private Integer requestLimit;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
