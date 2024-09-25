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
 * @desc 存储桶管理 BucketVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "存储桶管理")
public class BucketVO implements Serializable {
    private static final long serialVersionUID = -20900046798199937L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 存储桶编码
     */
    @Schema(description = "存储桶编码")
    private String bucketCode;
    /**
     * 存储桶名称
     */
    @Schema(description = "存储桶名称")
    private String bucketName;
    /**
     * 存储桶标签
     */
    @Schema(description = "存储桶标签")
    private String bucketTags;
    /**
     * 存储桶所属区域
     */
    @Schema(description = "存储桶所属区域")
    private String bucketRegion;
    /**
     * 访问权限（1Private 2Public 3Custom）
     */
    @Schema(description = "访问权限（1Private 2Public 3Custom）")
    private Integer accessPolicy;
    /**
     * 使用空间
     */
    @Schema(description = "使用空间")
    private String useSpace;
    /**
     * 配额空间
     */
    @Schema(description = "配额空间")
    private String hardQuota;
    /**
     * 是否加密（0停用 1启用）
     */
    @Schema(description = "是否加密（0停用 1启用）")
    private Integer encryption;
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
