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
 * @desc 刷新令牌表 RefreshTokenDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "刷新令牌表")
public class RefreshTokenPutDTO implements Serializable {
    private static final long serialVersionUID = -94840920411777277L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;

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
     * 最新的刷新令牌
     */
    @Schema(description = "最新的刷新令牌")
    private String latestRefreshToken;

    /**
     * 是否已用（0正常 1已用）
     */
    @Schema(description = "是否已用（0正常 1已用）")
    private Integer used;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
