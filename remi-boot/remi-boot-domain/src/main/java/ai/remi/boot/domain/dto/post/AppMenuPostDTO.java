package ai.remi.boot.domain.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用菜单表 AppMenuDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "应用菜单表")
public class AppMenuPostDTO implements Serializable {
    private static final long serialVersionUID = -45343704045413910L;


    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;

    /**
     * 应用编码
     */
    @Schema(description = "应用编码")
    private String appCode;

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
