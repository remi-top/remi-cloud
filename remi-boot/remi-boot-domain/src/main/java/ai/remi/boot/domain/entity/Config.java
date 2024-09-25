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
 * @desc 全局配置 ConfigDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_config")
public class Config extends BaseEntity {


    /**
     * 关联表ID
     */
    private String bindId;

    /**
     * 关联表编码
     */
    private String bindCode;

    /**
     * 配置项类型（1用户信息 2应用配置 3主题配置 4邮件配置 5存储配置 6支付配置 99其他配置）
     */
    private Integer confType;

    /**
     * 配置项编码
     */
    private String confCode;

    /**
     * 配置项名称
     */
    private String confName;

    /**
     * 配置项的值
     */
    private String confValue;

    /**
     * 字典项排序
     */
    private Integer sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
