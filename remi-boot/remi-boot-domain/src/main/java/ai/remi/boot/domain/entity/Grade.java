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
 * @desc 职系表 GradeDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_grade")
public class Grade extends BaseEntity {


    /**
     * 职系编码
     */
    private String gradeCode;

    /**
     * 职系名称
     */
    private String gradeName;

    /**
     * 职系英文名称
     */
    private String gradeNameEn;

    /**
     * 职系描述
     */
    private String gradeRemark;

    /**
     * 职系英文描述
     */
    private String gradeRemarkEn;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
