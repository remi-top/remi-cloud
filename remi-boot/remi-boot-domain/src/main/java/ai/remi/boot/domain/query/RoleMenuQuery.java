package ai.remi.boot.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 角色菜单表 RoleMenuQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "角色菜单表")
public class RoleMenuQuery implements Serializable {
    private static final long serialVersionUID = -23217192281524695L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
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
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private String menuId;
    /**
     * 菜单编码
     */
    @Schema(description = "菜单编码")
    private String menuCode;
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;

}

