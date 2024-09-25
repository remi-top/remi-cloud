package ai.remi.boot.domain.dto.post;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 授权码表 OauthCodeDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "授权码表")
public class OauthCodePostDTO implements Serializable {
    private static final long serialVersionUID = 519379491730333595L;

    /**
     * 分配给应用的appKey
     */
    @Schema(description = "分配给应用的appKey")
    private String appKey;

    /**
     * 授权类型，此值固定为code
     */
    @Schema(description = "授权类型，此值固定为code")
    private String authType;

    /**
     * 授权码
     */
    @Schema(description = "授权码")
    private String authCode;

    /**
     * 登录凭证
     */
    @Schema(description = "登录凭证")
    private String ticket;

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
     * 成功授权后的回调地址
     * 必须是注册appKey时填写的主域名下的地址，建议设置为网站首页或网站的用户中心。
     * 注意需要将url进行URLEncode。
     */
    @Schema(description = "成功授权后的回调地址")
    private String redirectUrl;

    /**
     * client端的状态值
     * 用于第三方应用防止CSRF攻击，成功授权后回调时会原样带回。
     * 请务必严格按照流程检查用户与state参数状态的绑定。
     */
    @Schema(description = "client端的状态值")
    private String state;

    /**
     * 过期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "过期时间")
    private LocalDateTime expiresTime;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
