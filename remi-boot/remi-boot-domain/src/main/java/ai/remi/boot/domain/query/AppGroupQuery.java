package ai.remi.boot.domain.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用组织 AppGroupQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "应用组织")
public class AppGroupQuery implements Serializable {
    private static final long serialVersionUID = 333971168853991903L;
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
     * 组织ID
     */
    @Schema(description = "组织ID")
    private String groupId;
    /**
     * 组织编码
     */
    @Schema(description = "组织编码")
    private String groupCode;
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;

}

