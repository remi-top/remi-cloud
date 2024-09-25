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
 * @desc 组织角色表 CordRoleDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_cord_role")
public class CordRole extends BaseEntity {


    /**
     * 组织ID
     */
    private String cordId;

    /**
     * 组织编码
     */
    private String cordCode;

    /**
     * 组织类型（1:公司 2:部门）
     */
    private Integer cordType;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
