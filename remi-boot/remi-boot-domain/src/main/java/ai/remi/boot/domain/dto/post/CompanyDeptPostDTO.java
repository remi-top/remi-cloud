package ai.remi.boot.domain.dto.post;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 公司部门表 CompanyDeptDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "公司部门表")
public class CompanyDeptPostDTO implements Serializable {
    
    private static final long serialVersionUID = 491352010046788372L;
    
    
    /**
     * 公司ID
     */
    @Schema(description = "公司ID")
    private String companyId;
    
    /**
     * 公司编码
     */
    @Schema(description = "公司编码")
    private String companyCode;
    
    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private String deptId;
    
    /**
     * 部门编码
     */
    @Schema(description = "部门编码")
    private String deptCode;
    
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;
    
    
}
