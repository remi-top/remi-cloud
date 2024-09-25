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
 * @desc 应用组织 AppGroupDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_app_group")
public class AppGroup extends BaseEntity {


    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 组织ID
     */
    private String groupId;

    /**
     * 组织编码
     */
    private String groupCode;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
