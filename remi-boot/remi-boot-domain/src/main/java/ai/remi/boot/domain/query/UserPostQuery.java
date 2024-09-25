package ai.remi.boot.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户岗位表 UserPostQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "用户岗位表")
public class UserPostQuery implements Serializable {
    private static final long serialVersionUID = -39150966488323367L;
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
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;

}

