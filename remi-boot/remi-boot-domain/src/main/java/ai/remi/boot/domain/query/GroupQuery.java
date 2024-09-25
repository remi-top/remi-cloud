package ai.remi.boot.domain.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 业务组织 GroupQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "业务组织")
public class GroupQuery implements Serializable {
    private static final long serialVersionUID = -38485734726883863L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private String parentId;
    /**
     * 用户组编码
     */
    @Schema(description = "用户组编码")
    private String groupCode;
    /**
     * 用户组名称
     */
    @Schema(description = "用户组名称")
    private String groupName;
    /**
     * 用户组英文名称
     */
    @Schema(description = "用户组英文名称")
    private String groupNameEn;
    /**
     * 用户组描述
     */
    @Schema(description = "用户组描述")
    private String groupRemark;
    /**
     * 用户组英文描述
     */
    @Schema(description = "用户组英文描述")
    private String groupRemarkEn;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;

}

