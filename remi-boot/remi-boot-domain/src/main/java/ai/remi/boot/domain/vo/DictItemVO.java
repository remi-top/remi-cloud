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
 * @desc 字典项表 DictItemVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "字典项表")
public class DictItemVO implements Serializable {
    private static final long serialVersionUID = 490657461927476614L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    private String dictId;
    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictCode;
    /**
     * 字典项值
     */
    @Schema(description = "字典项值")
    private String itemValue;
    /**
     * 字典项文本
     */
    @Schema(description = "字典项文本")
    private String itemText;
    /**
     * 字典项英文文本
     */
    @Schema(description = "字典项英文文本")
    private String itemTextEn;
    /**
     * 字典项描述
     */
    @Schema(description = "字典项描述")
    private String itemRemark;
    /**
     * 字典项英文描述
     */
    @Schema(description = "字典项英文描述")
    private String itemRemarkEn;
    /**
     * 是否默认（0否 1是）
     */
    @Schema(description = "是否默认（0否 1是）")
    private Integer isDefault;
    /**
     * 字典项样式
     */
    @Schema(description = "字典项样式")
    private String itemStyle;
    /**
     * 字典项CSS
     */
    @Schema(description = "字典项CSS")
    private String itemCss;
    /**
     * 字典项排序
     */
    @Schema(description = "字典项排序")
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
