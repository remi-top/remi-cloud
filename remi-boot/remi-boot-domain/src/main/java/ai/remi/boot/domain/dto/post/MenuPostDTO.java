package ai.remi.boot.domain.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 菜单表 MenuDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "菜单表")
public class MenuPostDTO implements Serializable {
    private static final long serialVersionUID = -40153846627060182L;


    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private String parentId;

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
     * 菜单编码
     */
    @Schema(description = "菜单编码")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    /**
     * 菜单英文名称
     */
    @Schema(description = "菜单英文名称")
    private String menuNameEn;

    /**
     * 菜单类型（1目录 2菜单 3操作）
     */
    @Schema(description = "菜单类型（1目录 2菜单 3操作）")
    private Integer menuType;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String menuIcon;

    /**
     * 菜单描述
     */
    @Schema(description = "菜单描述")
    private String menuRemark;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String menuPath;

    /**
     * 接口地址
     */
    @Schema(description = "接口地址")
    private String controllerPath;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String menuComponent;

    /**
     * 权限标识（应用编码:菜单编码:接口地址）
     */
    @Schema(description = "权限标识（应用编码:菜单编码:接口地址）")
    private String menuPermission;

    /**
     * 是否创建快捷方式（0停用 1启用）
     */
    @Schema(description = "是否创建快捷方式（0停用 1启用）")
    private Integer menuShortcut;

    /**
     * 是否缓存（0实时 1缓存）
     */
    @Schema(description = "是否缓存（0实时 1缓存）")
    private Integer keepAlive;

    /**
     * 是否可见（0隐藏 1显示）
     */
    @Schema(description = "是否可见（0隐藏 1显示）")
    private Integer visible;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
