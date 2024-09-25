package ai.remi.boot.domain.dto.put;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 组织角色表 CordRoleDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "组织角色表")
public class CordRolePutDTO implements Serializable {
    private static final long serialVersionUID = -42943587955941126L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;

    /**
     * 组织ID
     */
    @Schema(description = "组织ID")
    private String cordId;

    /**
     * 组织编码
     */
    @Schema(description = "组织编码")
    private String cordCode;

    /**
     * 组织类型（1:公司 2:部门）
     */
    @Schema(description = "组织类型（1:公司 2:部门）")
    private Integer cordType;

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
