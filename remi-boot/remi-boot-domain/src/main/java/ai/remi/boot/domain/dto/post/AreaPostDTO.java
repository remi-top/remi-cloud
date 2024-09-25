package ai.remi.boot.domain.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 省市县表 AreaDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "省市县表")
public class AreaPostDTO implements Serializable {
    private static final long serialVersionUID = -72165589670752682L;


    /**
     * 父区域ID
     */
    @Schema(description = "父区域ID")
    private String parentId;

    /**
     * 父区域编码
     */
    @Schema(description = "父区域编码")
    private String parentCode;

    /**
     * 区域编码
     */
    @Schema(description = "区域编码")
    private String areaCode;

    /**
     * 区域名称
     */
    @Schema(description = "区域名称")
    private String areaName;

    /**
     * 区域英文名称
     */
    @Schema(description = "区域英文名称")
    private String areaNameEn;

    /**
     * 区域备注
     */
    @Schema(description = "区域备注")
    private Long areaRemark;

    /**
     * 区域英文备注
     */
    @Schema(description = "区域英文备注")
    private Long areaRemarkEn;

    /**
     * 邮政编码
     */
    @Schema(description = "邮政编码")
    private String postalCode;

    /**
     * 区域级别（1-5,省市县镇村）
     */
    @Schema(description = "区域级别（1-5,省市县镇村）")
    private Integer areaLevel;

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
