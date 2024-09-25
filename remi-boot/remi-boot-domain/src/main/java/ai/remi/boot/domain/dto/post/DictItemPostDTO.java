package ai.remi.boot.domain.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 字典项表 DictItemDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "字典项表")
public class DictItemPostDTO implements Serializable {
    private static final long serialVersionUID = -75846227096968798L;


    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    private String dictId;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictCode;

    /**
     * 字典项值
     */
    @Schema(description = "字典项值")
    private String itemValue;

    /**
     * 字典项文本
     */
    @Schema(description = "字典项文本")
    private String itemText;

    /**
     * 字典项英文文本
     */
    @Schema(description = "字典项英文文本")
    private String itemTextEn;

    /**
     * 字典项描述
     */
    @Schema(description = "字典项描述")
    private String itemRemark;

    /**
     * 字典项英文描述
     */
    @Schema(description = "字典项英文描述")
    private String itemRemarkEn;

    /**
     * 是否默认（0否 1是）
     */
    @Schema(description = "是否默认（0否 1是）")
    private Integer isDefault;

    /**
     * 字典项样式
     */
    @Schema(description = "字典项样式")
    private String itemStyle;

    /**
     * 字典项CSS
     */
    @Schema(description = "字典项CSS")
    private String itemCss;

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
