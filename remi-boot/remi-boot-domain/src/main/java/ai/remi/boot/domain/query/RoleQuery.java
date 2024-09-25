package ai.remi.boot.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 角色表 RoleQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "角色表")
public class RoleQuery implements Serializable {
    private static final long serialVersionUID = 370120918839352802L;
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

}

