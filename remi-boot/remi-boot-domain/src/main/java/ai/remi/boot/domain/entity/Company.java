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
 * @desc 公司表 CompanyDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_company")
public class Company extends BaseEntity {
    
    
    /**
     * 父公司ID
     */
    private String parentId;
    
    /**
     * 公司编码
     */
    private String companyCode;
    
    /**
     * 公司名称
     */
    private String companyName;
    
    /**
     * 公司英文名称
     */
    private String companyNameEn;
    
    /**
     * 公司描述
     */
    private String companyRemark;
    
    /**
     * 公司英文描述
     */
    private String companyRemarkEn;
    
    /**
     * 公司地址
     */
    private String companyAddress;
    
    /**
     * 公司英文地址
     */
    private String companyAddressEn;
    
    /**
     * 公司邮编
     */
    private String companyPostcode;
    
    /**
     * 传真号码
     */
    private String companyFax;
    
    /**
     * 微信二维码
     */
    private String companyWechat;
    
    /**
     * 统一信用社会代码
     */
    private String companyBlicense;
    
    /**
     * 其它信息
     */
    private String companyOther;
    
    /**
     * 显示顺序
     */
    private Integer sort;
    
    /**
     * 公司法人ID
     */
    private String companyHeadId;
    
    /**
     * 公司法人工号
     */
    private String companyHeadCode;
    
    /**
     * 手机号码
     */
    private String mobile;
    
    /**
     * 电话号码
     */
    private String phone;
    
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;
    
    
}
