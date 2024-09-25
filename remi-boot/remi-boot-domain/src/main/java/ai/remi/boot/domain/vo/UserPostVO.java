package ai.remi.boot.domain.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户岗位表 UserPostVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "用户岗位表")
public class UserPostVO implements Serializable {
    private static final long serialVersionUID = 707780665135947363L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;
    /**
     * 用户编码
     */
    @Schema(description = "用户编码")
    private String userCode;
    /**
     * 职位ID
     */
    @Schema(description = "职位ID")
    private String positionId;

    /**
     * 职位编码
     */
    @Schema(description = "职位编码")
    private String positionCode;

    /**
     * 职位名称
     */
    @Schema(description = "职位名称")
    private String positionName;

    /**
     * 岗位英文名称
     */
    @Schema(description = "职位英文名称")
    private String positionNameEn;

    /**
     * 职级ID
     */
    @Schema(description = "职级ID")
    private String rankId;

    /**
     * 职级编码
     */
    @Schema(description = "职级编码")
    private String rankCode;

    /**
     * 职级名称
     */
    @Schema(description = "职级名称")
    private String rankName;

    /**
     * 职级英文名称
     */
    @Schema(description = "职级英文名称")
    private String rankNameEn;
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
