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
 * @desc 应用发布表 AppPublishDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "应用发布表")
public class AppPublishPutDTO implements Serializable {
    private static final long serialVersionUID = 746935977976598962L;

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
     * 应用编码（对内唯一标识）
     */
    @Schema(description = "应用编码（对内唯一标识）")
    private String appCode;

    /**
     * 应用key（对外唯一标识）
     */
    @Schema(description = "应用key（对外唯一标识）")
    private String appKey;

    /**
     * 应用的版本
     */
    @Schema(description = "应用的版本")
    private String appVersion;

    /**
     * 适用的启迪平台版本
     */
    @Schema(description = "适用的启迪平台版本")
    private String sdtVersion;

    /**
     * 文件名称
     */
    @Schema(description = "文件名称")
    private String fileName;

    /**
     * 文件地址 , 默认相对路径
     */
    @Schema(description = "文件地址 , 默认相对路径")
    private String fileUrl;

    /**
     * 应用大小 , 单位字节
     */
    @Schema(description = "应用大小 , 单位字节")
    private Integer fileSize;

    /**
     * 审核人
     */
    @Schema(description = "审核人")
    private String auditUser;

    /**
     * 审核时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "审核时间")
    private LocalDateTime aduitTime;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
