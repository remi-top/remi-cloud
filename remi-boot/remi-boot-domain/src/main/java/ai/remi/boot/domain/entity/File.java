package ai.remi.boot.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import ai.remi.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文件管理 FileDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_file")
public class File extends BaseEntity {


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


}
