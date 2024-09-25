package ai.remi.boot.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 部门表 DeptQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "部门表")
public class DeptQuery implements Serializable {
    
    private static final long serialVersionUID = -83624438522744667L;
    
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
    
}

