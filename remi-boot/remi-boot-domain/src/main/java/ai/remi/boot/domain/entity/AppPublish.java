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
 * @desc 应用发布表 AppPublishDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_app_publish")
public class AppPublish extends BaseEntity {


    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用编码（对内唯一标识）
     */
    private String appCode;

    /**
     * 应用key（对外唯一标识）
     */
    private String appKey;

    /**
     * 应用的版本
     */
    private String appVersion;

    /**
     * 适用的启迪平台版本
     */
    private String sdtVersion;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件地址 , 默认相对路径
     */
    private String fileUrl;

    /**
     * 应用大小 , 单位字节
     */
    private Integer fileSize;

    /**
     * 审核人
     */
    private String auditUser;

    /**
     * 审核时间
     */
    private LocalDateTime aduitTime;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
