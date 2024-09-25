package ai.remi.boot.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文件管理 FileQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "文件管理")
public class FileQuery implements Serializable {
    private static final long serialVersionUID = 185660917436209063L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
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
     * 文件编号
     */
    @Schema(description = "文件编号")
    private String fileCode;
    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    private String fileType;
    /**
     * 文件大小（KiB）
     */
    @Schema(description = "文件大小（KiB）")
    private String fileSize;
    /**
     * 文件标签
     */
    @Schema(description = "文件标签")
    private String fileTags;
    /**
     * 文件名称
     */
    @Schema(description = "文件名称")
    private String fileName;
    /**
     * 文件目录
     */
    @Schema(description = "文件目录")
    private String filePath;
    /**
     * 原文件名
     */
    @Schema(description = "原文件名")
    private String original;
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;

}

