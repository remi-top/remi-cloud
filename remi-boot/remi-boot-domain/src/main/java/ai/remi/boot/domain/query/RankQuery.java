package ai.remi.boot.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 职级表 RankQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "职级表")
public class RankQuery implements Serializable {
    private static final long serialVersionUID = -55241052647022281L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 职位ID
     */
    @Schema(description = "职位ID")
    private String positionId;
    /**
     * 职位编码
     */
    @Schema(description = "职位编码")
    private String positionCode;
    /**
     * 职级编码
     */
    @Schema(description = "职级编码")
    private String rankCode;
    /**
     * 职级名称
     */
    @Schema(description = "职级名称")
    private String rankName;
    /**
     * 职级英文名称
     */
    @Schema(description = "职级英文名称")
    private String rankNameEn;
    /**
     * 职级描述
     */
    @Schema(description = "职级描述")
    private String rankRemark;
    /**
     * 职级英文描述
     */
    @Schema(description = "职级英文描述")
    private String rankRemarkEn;
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

