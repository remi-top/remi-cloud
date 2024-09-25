package ai.remi.boot.domain.tree;

import com.fasterxml.jackson.annotation.JsonFormat;
import ai.remi.comm.domain.tree.TreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Remi
 * @email startdis@dianjiukeji.cn
 * @desc 菜单表 MenuVO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "菜单表")
public class MenuTree extends TreeNode<MenuTree> implements Serializable {
    private static final long serialVersionUID = 505874294590378764L;
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
    /**
     * 是否删除（0正常 1删除）
     */
    @Schema(description = "是否删除（0正常 1删除）")
    private Integer deleted;
    /**
     * 乐观锁
     */
    @Schema(description = "乐观锁")
    private Long revision;
    /**
     * 部门租户ID
     */
    @Schema(description = "部门租户ID")
    private String deptTenantId;
    /**
     * 公司租户ID
     */
    @Schema(description = "公司租户ID")
    private String companyTenantId;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updatedBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

}
