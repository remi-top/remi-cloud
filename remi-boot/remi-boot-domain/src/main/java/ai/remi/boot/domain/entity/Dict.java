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
 * @desc 字典主表 DictDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_dict")
public class Dict extends BaseEntity {


    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典英文名称
     */
    private String dictNameEn;

    /**
     * 字典描述
     */
    private String dictRemark;

    /**
     * 字典英文描述
     */
    private String dictRemarkEn;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
