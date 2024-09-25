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
 * @desc 应用发布表 AppPublishVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "应用发布表")
public class AppPublishVO implements Serializable {
    private static final long serialVersionUID = 789518671530935063L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 应用编码（对内唯一标识）
     */
    @Schema(description = "应用编码（对内唯一标识）")
    private String appCode;
    /**
     * 应用key（对外唯一标识）
     */
    @Schema(description = "应用key（对外唯一标识）")
    private String appKey;
    /**
     * 应用的版本
     */
    @Schema(description = "应用的版本")
    private String appVersion;
    /**
     * 适用的启迪平台版本
     */
    @Schema(description = "适用的启迪平台版本")
    private String sdtVersion;
    /**
     * 文件名称
     */
    @Schema(description = "文件名称")
    private String fileName;
    /**
     * 文件地址 , 默认相对路径
     */
    @Schema(description = "文件地址 , 默认相对路径")
    private String fileUrl;
    /**
     * 应用大小 , 单位字节
     */
    @Schema(description = "应用大小 , 单位字节")
    private Integer fileSize;
    /**
     * 审核人
     */
    @Schema(description = "审核人")
    private String auditUser;
    /**
     * 审核时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "审核时间")
    private LocalDateTime aduitTime;
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
