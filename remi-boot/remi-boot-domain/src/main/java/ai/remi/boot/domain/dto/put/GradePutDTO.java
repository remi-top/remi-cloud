package ai.remi.boot.domain.dto.put;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 职系表 GradeDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "职系表")
public class GradePutDTO implements Serializable {
    private static final long serialVersionUID = -38430079686761968L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;

    /**
     * 职系编码
     */
    @Schema(description = "职系编码")
    private String gradeCode;

    /**
     * 职系名称
     */
    @Schema(description = "职系名称")
    private String gradeName;

    /**
     * 职系英文名称
     */
    @Schema(description = "职系英文名称")
    private String gradeNameEn;

    /**
     * 职系描述
     */
    @Schema(description = "职系描述")
    private String gradeRemark;

    /**
     * 职系英文描述
     */
    @Schema(description = "职系英文描述")
    private String gradeRemarkEn;

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
