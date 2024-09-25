package ai.remi.boot.domain.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 字典主表 DictDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "字典主表")
public class DictPostDTO implements Serializable {
    private static final long serialVersionUID = 931660359810842564L;


    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictCode;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典英文名称
     */
    @Schema(description = "字典英文名称")
    private String dictNameEn;

    /**
     * 字典描述
     */
    @Schema(description = "字典描述")
    private String dictRemark;

    /**
     * 字典英文描述
     */
    @Schema(description = "字典英文描述")
    private String dictRemarkEn;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
