package ai.remi.boot.domain.dto.put;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 鉴权令牌表 AccessTokenDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "鉴权令牌表")
public class AccessTokenPutDTO implements Serializable {
    private static final long serialVersionUID = -68449462646208801L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;

    /**
     * 应用标识
     */
    @Schema(description = "应用标识")
    private String appKey;

    /**
     * 应用密钥
     */
    @Schema(description = "应用密钥")
    private String appSecret;

    /**
     * 鉴权令牌
     */
    @Schema(description = "鉴权令牌")
    private String accessToken;

    /**
     * 刷新令牌
     */
    @Schema(description = "刷新令牌")
    private String refreshToken;

    /**
     * 过期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "过期时间")
    private LocalDateTime expiresTime;

    /**
     * 令牌类型（bearer mac）
     */
    @Schema(description = "令牌类型（bearer mac）")
    private String tokenType;

    /**
     * 权限范围
     */
    @Schema(description = "权限范围")
    private String tokenScope;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
