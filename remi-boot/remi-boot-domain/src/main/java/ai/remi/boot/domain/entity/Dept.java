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
 * @desc 部门表 DeptDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_dept")
public class Dept extends BaseEntity {
    
    
    /**
     * 父部门ID
     */
    private String parentId;
    
    /**
     * 部门编码
     */
    private String deptCode;
    
    /**
     * 部门名称
     */
    private String deptName;
    
    /**
     * 部门英文名称
     */
    private String deptNameEn;
    
    /**
     * 部门领导ID
     */
    private String deptHeadId;
    
    /**
     * 部门领导工号
     */
    private String deptHeadCode;
    
    /**
     * 显示顺序
     */
    private Integer sort;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 联系邮箱
     */
    private String email;
    
    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;
    
    
}
