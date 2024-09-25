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
 * @desc 部门表 DeptVO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "业务组织")
public class GroupTree extends TreeNode<GroupTree> implements Serializable {

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
    /**
     * 是否删除（0正常 1删除）
     */
    @Schema(description = "是否删除（0正常 1删除）")
    private Integer deleted;
    /**
     * 乐观锁
     */
    @Schema(description = "乐观锁")
    private Integer revision;
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
