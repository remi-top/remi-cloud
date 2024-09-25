package ai.remi.boot.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 公司表 CompanyVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "公司表")
public class CompanyVO implements Serializable {
    
    private static final long serialVersionUID = -74086054099298467L;
    
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    
    /**
     * 父公司ID
     */
    @Schema(description = "父公司ID")
    private String parentId;
    
    /**
     * 公司编码
     */
    @Schema(description = "公司编码")
    private String companyCode;
    
    /**
     * 公司名称
     */
    @Schema(description = "公司名称")
    private String companyName;
    
    /**
     * 公司英文名称
     */
    @Schema(description = "公司英文名称")
    private String companyNameEn;
    
    /**
     * 公司描述
     */
    @Schema(description = "公司描述")
    private String companyRemark;
    
    /**
     * 公司英文描述
     */
    @Schema(description = "公司英文描述")
    private String companyRemarkEn;
    
    /**
     * 公司地址
     */
    @Schema(description = "公司地址")
    private String companyAddress;
    
    /**
     * 公司英文地址
     */
    @Schema(description = "公司英文地址")
    private String companyAddressEn;
    
    /**
     * 公司邮编
     */
    @Schema(description = "公司邮编")
    private String companyPostcode;
    
    /**
     * 传真号码
     */
    @Schema(description = "传真号码")
    private String companyFax;
    
    /**
     * 微信二维码
     */
    @Schema(description = "微信二维码")
    private String companyWechat;
    
    /**
     * 统一信用社会代码
     */
    @Schema(description = "统一信用社会代码")
    private String companyBlicense;
    
    /**
     * 其它信息
     */
    @Schema(description = "其它信息")
    private String companyOther;
    
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    
    /**
     * 公司法人ID
     */
    @Schema(description = "公司法人ID")
    private String companyHeadId;
    
    /**
     * 公司法人工号
     */
    @Schema(description = "公司法人工号")
    private String companyHeadCode;
    
    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String mobile;
    
    /**
     * 电话号码
     */
    @Schema(description = "电话号码")
    private String phone;
    
    /**
     * 电子邮箱
     */
    @Schema(description = "电子邮箱")
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
