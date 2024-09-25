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
 * @desc 公司部门表 CompanyDeptDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_company_dept")
public class CompanyDept extends BaseEntity {
    
    
    /**
     * 公司ID
     */
    private String companyId;
    
    /**
     * 公司编码
     */
    private String companyCode;
    
    /**
     * 部门ID
     */
    private String deptId;
    
    /**
     * 部门编码
     */
    private String deptCode;
    
    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;
    
    
}
