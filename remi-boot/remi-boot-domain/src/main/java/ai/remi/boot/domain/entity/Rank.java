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
 * @desc 职级表 RankDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_rank")
public class Rank extends BaseEntity {


    /**
     * 职位ID
     */
    private String positionId;

    /**
     * 职位编码
     */
    private String positionCode;

    /**
     * 职级编码
     */
    private String rankCode;

    /**
     * 职级名称
     */
    private String rankName;

    /**
     * 职级英文名称
     */
    private String rankNameEn;

    /**
     * 职级描述
     */
    private String rankRemark;

    /**
     * 职级英文描述
     */
    private String rankRemarkEn;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
