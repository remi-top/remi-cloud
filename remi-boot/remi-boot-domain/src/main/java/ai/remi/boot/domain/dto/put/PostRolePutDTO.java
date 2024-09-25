package ai.remi.boot.domain.dto.put;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 职位角色表 PostRoleDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "职位角色表")
public class PostRolePutDTO implements Serializable {
    private static final long serialVersionUID = -49329405706305580L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;

    /**
     * 职位ID
     */
    @Schema(description = "职位ID")
    private String postId;

    /**
     * 职位编码
     */
    @Schema(description = "职位编码")
    private String postCode;

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
