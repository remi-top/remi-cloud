package ai.remi.boot.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 部门用户表 DeptUserQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "部门用户表")
public class DeptUserQuery implements Serializable {
    private static final long serialVersionUID = -98435172100548102L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private String deptId;
    /**
     * 部门编码
     */
    @Schema(description = "部门编码")
    private String deptCode;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;
    /**
     * 用户编码
     */
    @Schema(description = "用户编码")
    private String userCode;
    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;

}

