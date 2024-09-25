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
 * @desc 省市县表 AreaDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_area")
public class Area extends BaseEntity {


    /**
     * 父区域ID
     */
    private String parentId;

    /**
     * 父区域编码
     */
    private String parentCode;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域英文名称
     */
    private String areaNameEn;

    /**
     * 区域备注
     */
    private Long areaRemark;

    /**
     * 区域英文备注
     */
    private Long areaRemarkEn;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 区域级别（1-5,省市县镇村）
     */
    private Integer areaLevel;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;

}
