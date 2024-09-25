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
 * @desc 用户角色表 UserRoleDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_user_role")
public class UserRole extends BaseEntity {


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户编码
     */
    private String userCode;

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
