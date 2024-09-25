package ai.remi.boot.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 组织角色表 CordRoleVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "组织角色表")
public class CordRoleVO implements Serializable {
    private static final long serialVersionUID = 192018737211064486L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 组织ID
     */
    @Schema(description = "组织ID")
    private String cordId;
    /**
     * 组织编码
     */
    @Schema(description = "组织编码")
    private String cordCode;
    /**
     * 组织类型（1:公司 2:部门）
     */
    @Schema(description = "组织类型（1:公司 2:部门）")
    private Integer cordType;
    /**
     * 公司名称
     */
    @Schema(description = "公司名称")
    private String companyName;
    /**
     * 公司英文名称
     */
    @Schema(description = "公司英文名称")
    private String companyNameEn;
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 部门英文名称
     */
    @Schema(description = "部门英文名称")
    private String deptNameEn;
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;
    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String roleCode;
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
