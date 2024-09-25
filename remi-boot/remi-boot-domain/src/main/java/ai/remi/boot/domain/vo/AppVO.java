package ai.remi.boot.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用表Application AppVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "应用表Application")
public class AppVO implements Serializable {
    private static final long serialVersionUID = 581941810648655774L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 应用编码（对内唯一标识）
     */
    @Schema(description = "应用编码（对内唯一标识）")
    private String appCode;
    /**
     * 应用key（对外唯一标识）
     */
    @Schema(description = "应用key（对外唯一标识）")
    private String appKey;
    /**
     * 应用密钥
     */
    @Schema(description = "应用密钥")
    private String appSecret;
    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    private String appName;
    /**
     * 应用英文名称
     */
    @Schema(description = "应用英文名称")
    private String appNameEn;
    /**
     * 应用描述
     */
    @Schema(description = "应用描述")
    private String appRemark;
    /**
     * 应用英文描述
     */
    @Schema(description = "应用英文描述")
    private String appRemarkEn;
    /**
     * 应用图标（如：icon-zuopin）
     */
    @Schema(description = "应用图标（如：icon-zuopin）")
    private String appIcon;

    /**
     * 应用图标颜色（如：#13C2C2）
     */
    @Schema(description = "应用图标颜色（如：#13C2C2）")
    private String appIconColor;

    /**
     * 应用备案号
     */
    @Schema(description = "应用备案号")
    private String appIcp;

    /**
     * 是否公共应用（0否 1是）
     */
    @Schema(description = "是否公共应用（0否 1是）")
    private String isCommon;

    /**
     * 是否移动端应用（0否 1是）
     */
    @Schema(description = "是否移动端应用（0否 1是）")
    private String isMobile;

    /**
     * 应用的全局配置
     */
    @Schema(description = "应用全局配置")
    private List<ConfigVO> appConfigs;

    /**
     * 每秒间隔访问次数限制
     */
    @Schema(description = "每秒间隔访问次数限制")
    private Integer requestLimit;
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;
    /**
     * 是否删除（0正常 1删除）
     */
    @Schema(description = "是否删除（0正常 1删除）")
    private Integer deleted;
    /**
     * 乐观锁
     */
    @Schema(description = "乐观锁")
    private Long revision;
    /**
     * 部门租户ID
     */
    @Schema(description = "部门租户ID")
    private String deptTenantId;
    /**
     * 公司租户ID
     */
    @Schema(description = "公司租户ID")
    private String companyTenantId;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updatedBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

}
