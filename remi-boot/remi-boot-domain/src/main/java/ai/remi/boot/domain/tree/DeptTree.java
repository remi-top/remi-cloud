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
@Schema(description = "部门表")
public class DeptTree extends TreeNode<DeptTree> implements Serializable {

    private static final long serialVersionUID = 505874294590378764L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;

    /**
     * 父部门ID
     */
    @Schema(description = "父部门ID")
    private String parentId;

    /**
     * 所属公司ID
     */
    @Schema(description = "所属公司ID")
    private String companyId;

    /**
     * 所属公司编码
     */
    @Schema(description = "所属公司编码")
    private String companyCode;

    /**
     * 部门编码
     */
    @Schema(description = "部门编码")
    private String deptCode;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 部门英文名称
     */
    @Schema(description = "部门英文名称")
    private String deptNameEn;

    /**
     * 部门领导ID
     */
    @Schema(description = "部门领导ID")
    private String deptHeadId;

    /**
     * 部门领导工号
     */
    @Schema(description = "部门领导工号")
    private String deptHeadCode;

    /**
     * 部门领导名称
     */
    @Schema(description = "部门领导名称")
    private String deptHeadName;

    /**
     * 部门领导英文名称
     */
    @Schema(description = "部门领导英文名称")
    private String deptHeadNameEn;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 联系邮箱
     */
    @Schema(description = "联系邮箱")
    private String email;

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
