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
 * @desc 省市县表 AreaVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "省市县表")
public class AreaVO implements Serializable {
    private static final long serialVersionUID = -39715980158854806L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 父区域ID
     */
    @Schema(description = "父区域ID")
    private String parentId;
    /**
     * 父区域编码
     */
    @Schema(description = "父区域编码")
    private String parentCode;
    /**
     * 区域编码
     */
    @Schema(description = "区域编码")
    private String areaCode;
    /**
     * 区域名称
     */
    @Schema(description = "区域名称")
    private String areaName;
    /**
     * 区域英文名称
     */
    @Schema(description = "区域英文名称")
    private String areaNameEn;
    /**
     * 区域备注
     */
    @Schema(description = "区域备注")
    private Long areaRemark;
    /**
     * 区域英文备注
     */
    @Schema(description = "区域英文备注")
    private Long areaRemarkEn;
    /**
     * 邮政编码
     */
    @Schema(description = "邮政编码")
    private String postalCode;
    /**
     * 区域级别（1-5,省市县镇村）
     */
    @Schema(description = "区域级别（1-5,省市县镇村）")
    private Integer areaLevel;
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
