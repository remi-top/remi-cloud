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
 * @desc 鉴权令牌表 AccessTokenVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "鉴权令牌表")
public class AccessTokenVO implements Serializable {
    private static final long serialVersionUID = -51779224417633225L;
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
     * 登录凭证
     */
    @Schema(description = "登录凭证")
    private String ticket;
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
