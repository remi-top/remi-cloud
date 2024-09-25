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
 * @desc 角色表 RoleVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "角色表")
public class RoleVO implements Serializable {
    private static final long serialVersionUID = -51280070502071685L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 角色代码
     */
    @Schema(description = "角色代码")
    private String roleCode;
    /**
     * 角色类型（1:部门角色 2:岗位角色 3:用户角色  4:组织角色）
     */
    @Schema(description = "角色类型（1:部门角色 2:岗位角色 3:用户角色  4:组织角色）")
    private Integer roleType;
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;
    /**
     * 角色英文名称
     */
    @Schema(description = "角色英文名称")
    private String roleNameEn;
    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String roleRemark;
    /**
     * 角色英文描述
     */
    @Schema(description = "角色英文描述")
    private String roleRemarkEn;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    /**
     * 数据范围（1:全部数据权限 2:自定数据权限 3:本部门数据权限 4:本部门及以下数据权限）
     */
    @Schema(description = "数据范围（1:全部数据权限 2:自定数据权限 3:本部门数据权限 4:本部门及以下数据权限）")
    private Integer roleScope;
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
