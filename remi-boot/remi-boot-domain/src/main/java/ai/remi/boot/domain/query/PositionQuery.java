package ai.remi.boot.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 职位表 PositionQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "职位表")
public class PositionQuery implements Serializable {
    private static final long serialVersionUID = -56650098058103203L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 职系ID
     */
    @Schema(description = "职系ID")
    private String gradeId;
    /**
     * 职系编码
     */
    @Schema(description = "职系编码")
    private String gradeCode;
    /**
     * 职位编码
     */
    @Schema(description = "职位编码")
    private String positionCode;
    /**
     * 职位名称
     */
    @Schema(description = "职位名称")
    private String positionName;
    /**
     * 岗位英文名称
     */
    @Schema(description = "岗位英文名称")
    private String positionNameEn;
    /**
     * 职位描述
     */
    @Schema(description = "职位描述")
    private String positionRemark;
    /**
     * 职位英文描述
     */
    @Schema(description = "职位英文描述")
    private String positionRemarkEn;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;

}

