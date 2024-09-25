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
 * @desc 业务组织 GroupDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_group")
public class Group extends BaseEntity {

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 用户组编码
     */
    private String groupCode;

    /**
     * 用户组名称
     */
    private String groupName;

    /**
     * 用户组英文名称
     */
    private String groupNameEn;

    /**
     * 用户组描述
     */
    private String groupRemark;

    /**
     * 用户组英文描述
     */
    private String groupRemarkEn;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
