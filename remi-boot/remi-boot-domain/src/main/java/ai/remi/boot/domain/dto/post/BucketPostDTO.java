package ai.remi.boot.domain.dto.post;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 存储桶管理 BucketDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "存储桶管理")
public class BucketPostDTO implements Serializable {
    private static final long serialVersionUID = -68143833409367473L;


    /**
     * 存储桶编码
     */
    @Schema(description = "存储桶编码")
    private String bucketCode;

    /**
     * 存储桶名称
     */
    @Schema(description = "存储桶名称")
    private String bucketName;

    /**
     * 存储桶标签
     */
    @Schema(description = "存储桶标签")
    private String bucketTags;

    /**
     * 存储桶所属区域
     */
    @Schema(description = "存储桶所属区域")
    private String bucketRegion;

    /**
     * 访问权限（1Private 2Public 3Custom）
     */
    @Schema(description = "访问权限（1Private 2Public 3Custom）")
    private Integer accessPolicy;

    /**
     * 是否加密（0停用 1启用）
     */
    @Schema(description = "是否加密（0停用 1启用）")
    private Integer encryption;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
