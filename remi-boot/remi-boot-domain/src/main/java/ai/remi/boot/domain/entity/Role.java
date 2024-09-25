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
 * @desc 角色表 RoleDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_role")
public class Role extends BaseEntity {


    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色类型（1:部门角色 2:岗位角色 3:用户角色  4:组织角色）
     */
    private Integer roleType;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色英文名称
     */
    private String roleNameEn;

    /**
     * 角色描述
     */
    private String roleRemark;

    /**
     * 角色英文描述
     */
    private String roleRemarkEn;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 数据范围（1:全部数据权限 2:自定数据权限 3:本部门数据权限 4:本部门及以下数据权限）
     */
    private Integer roleScope;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
