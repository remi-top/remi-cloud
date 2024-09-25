package ai.remi.boot.domain.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 全局配置 ConfigQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "全局配置")
public class ConfigQuery implements Serializable {
    private static final long serialVersionUID = -66106751763134864L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 关联表ID
     */
    @Schema(description = "关联表ID")
    private String bindId;
    /**
     * 关联表编码
     */
    @Schema(description = "关联表编码")
    private String bindCode;
    /**
     * 配置项类型（1用户信息 2应用配置 3主题配置 4邮件配置 5存储配置 6支付配置 99其他配置）
     */
    @Schema(description = "配置项类型（1用户信息 2应用配置 3主题配置 4邮件配置 5存储配置 6支付配置 99其他配置）")
    private Integer confType;
    /**
     * 配置项编码
     */
    @Schema(description = "配置项编码")
    private String confCode;
    /**
     * 配置项名称
     */
    @Schema(description = "配置项名称")
    private String confName;
    /**
     * 配置项的值
     */
    @Schema(description = "配置项的值")
    private String confValue;
    /**
     * 字典项排序
     */
    @Schema(description = "字典项排序")
    private Integer sort;
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;

}

