package ai.remi.boot.domain.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 黑白名单表 RestrictIpDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "黑白名单表")
public class RestrictIpPostDTO implements Serializable {
    private static final long serialVersionUID = -13516390011974946L;


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
     * 白名单IP
     */
    @Schema(description = "白名单IP")
    private String whiteIp;

    /**
     * 黑名单IP
     */
    @Schema(description = "黑名单IP")
    private String blackIp;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
