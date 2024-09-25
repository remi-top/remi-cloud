package ai.remi.boot.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import ai.remi.boot.domain.tree.GroupTree;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用组织 AppGroupVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "应用组织")
public class AppGroupVO implements Serializable {
    private static final long serialVersionUID = 874175552073119256L;
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
     * 应用编码
     */
    @Schema(description = "应用编码")
    private String appCode;

    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    private String appName;

    /**
     * 应用英文名称
     */
    @Schema(description = "应用英文名称")
    private String appNameEn;

    /**
     * 组织树集合
     */
    @Schema(description = "组织树集合")
    private List<GroupTree> groupTree;

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
