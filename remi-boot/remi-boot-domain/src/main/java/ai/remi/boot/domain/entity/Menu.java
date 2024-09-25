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
 * @desc 菜单表 MenuDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_menu")
public class Menu extends BaseEntity {


    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单英文名称
     */
    private String menuNameEn;

    /**
     * 菜单类型（1目录 2菜单 3操作）
     */
    private Integer menuType;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单描述
     */
    private String menuRemark;

    /**
     * 路由地址
     */
    private String menuPath;

    /**
     * 接口地址
     */
    private String controllerPath;

    /**
     * 组件路径
     */
    private String menuComponent;

    /**
     * 权限标识（应用编码:菜单编码:接口地址）
     */
    private String menuPermission;

    /**
     * 是否创建快捷方式（0停用 1启用）
     */
    private Integer menuShortcut;

    /**
     * 是否缓存（0实时 1缓存）
     */
    private Integer keepAlive;

    /**
     * 是否可见（0隐藏 1显示）
     */
    private Integer visible;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
