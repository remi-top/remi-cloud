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
 * @desc 存储桶管理 BucketDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_bucket")
public class Bucket extends BaseEntity {


    /**
     * 存储桶编码
     */
    private String bucketCode;

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 存储桶标签
     */
    private String bucketTags;

    /**
     * 存储桶所属区域
     */
    private String bucketRegion;

    /**
     * 访问权限（1Private 2Public 3Custom）
     */
    private Integer accessPolicy;

    /**
     * 使用空间
     */
    private String useSpace;

    /**
     * 配额空间
     */
    private String hardQuota;

    /**
     * 是否加密（0停用 1启用）
     */
    private Integer encryption;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
