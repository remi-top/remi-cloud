package ai.remi.boot.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import ai.remi.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 刷新令牌表 RefreshTokenDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_refresh_token")
public class RefreshToken extends BaseEntity {


    /**
     * 鉴权令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

    /**
     * 最新的刷新令牌
     */
    private String latestRefreshToken;

    /**
     * 是否已用（0正常 1已用）
     */
    private Integer used;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
