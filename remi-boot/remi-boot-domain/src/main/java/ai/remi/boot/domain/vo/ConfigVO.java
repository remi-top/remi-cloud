package ai.remi.boot.domain.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 全局配置 ConfigVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "全局配置")
public class ConfigVO implements Serializable {
    private static final long serialVersionUID = -28370834657201386L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 关联表ID
     */
    @Schema(description = "关联表ID")
    private String bindId;
    /**
     * 关联表编码
     */
    @Schema(description = "关联表编码")
    private String bindCode;
    /**
     * 配置项类型（1用户信息 2应用配置 3主题配置 4邮件配置 5存储配置 6支付配置 99其他配置）
     */
    @Schema(description = "配置项类型（1用户信息 2应用配置 3主题配置 4邮件配置 5存储配置 6支付配置 99其他配置）")
    private Integer confType;
    /**
     * 配置项编码
     */
    @Schema(description = "配置项编码")
    private String confCode;
    /**
     * 配置项名称
     */
    @Schema(description = "配置项名称")
    private String confName;
    /**
     * 配置项的值
     */
    @Schema(description = "配置项的值")
    private String confValue;
    /**
     * 字典项排序
     */
    @Schema(description = "字典项排序")
    private Integer sort;
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
    private Integer revision;
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
