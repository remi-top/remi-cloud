package ai.remi.comm.minio.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DianJiu 【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 文件管理 FileVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class FileS3 implements Serializable {
    private static final long serialVersionUID = -71908971735852628L;
    /**
     * ID
     */
    private String id;
    /**
     * 存储桶编码
     */
    private String bucketCode;
    /**
     * 存储桶名称
     */
    private String bucketName;
    /**
     * 文件编号
     */
    private String fileCode;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件大小（KiB）
     */
    private String fileSize;
    /**
     * 文件标签
     */
    private String fileTags;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件目录
     */
    private String filePath;
    /**
     * 原文件名
     */
    private String original;
    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;
    /**
     * 是否删除（0正常 1删除）
     */
    private Integer deleted;
    /**
     * 乐观锁
     */
    private Integer revision;
    /**
     * 部门租户ID
     */
    private String deptTenantId;
    /**
     * 公司租户ID
     */
    private String companyTenantId;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;

}
