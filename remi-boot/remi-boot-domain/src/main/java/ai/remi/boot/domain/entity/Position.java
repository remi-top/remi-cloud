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
 * @desc 职位表 PositionDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_position")
public class Position extends BaseEntity {


    /**
     * 职系ID
     */
    private String gradeId;

    /**
     * 职系编码
     */
    private String gradeCode;

    /**
     * 职位编码
     */
    private String positionCode;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 岗位英文名称
     */
    private String positionNameEn;

    /**
     * 职位描述
     */
    private String positionRemark;

    /**
     * 职位英文描述
     */
    private String positionRemarkEn;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
