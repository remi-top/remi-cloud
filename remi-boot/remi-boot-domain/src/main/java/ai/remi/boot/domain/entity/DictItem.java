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
 * @desc 字典项表 DictItemDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_dict_item")
public class DictItem extends BaseEntity {


    /**
     * 字典ID
     */
    private String dictId;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项英文文本
     */
    private String itemTextEn;

    /**
     * 字典项描述
     */
    private String itemRemark;

    /**
     * 字典项英文描述
     */
    private String itemRemarkEn;

    /**
     * 是否默认（0否 1是）
     */
    private Integer isDefault;

    /**
     * 字典项样式
     */
    private String itemStyle;

    /**
     * 字典项CSS
     */
    private String itemCss;

    /**
     * 字典项排序
     */
    private Integer sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
