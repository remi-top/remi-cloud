package ai.remi.boot.domain.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户组角色表 GroupRoleDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "用户组角色表")
public class GroupRolePostDTO implements Serializable {
    private static final long serialVersionUID = 147950794807960891L;


    /**
     * 用户组ID
     */
    @Schema(description = "用户组ID")
    private String groupId;

    /**
     * 用户组编码
     */
    @Schema(description = "用户组编码")
    private String groupCode;

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


}
