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
 * @desc 组织用户 GroupUserDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_group_user")
public class GroupUser extends BaseEntity {


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 分组ID
     */
    private String groupId;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
