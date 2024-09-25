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
 * @desc 黑白名单表 RestrictIpDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_restrict_ip")
public class RestrictIp extends BaseEntity {


    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用编码（对内唯一标识）
     */
    private String appCode;

    /**
     * 白名单IP
     */
    private String whiteIp;

    /**
     * 黑名单IP
     */
    private String blackIp;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
