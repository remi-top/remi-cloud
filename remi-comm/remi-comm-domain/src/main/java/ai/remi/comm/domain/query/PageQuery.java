package ai.remi.comm.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc PageQuery
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @NotBlank(message = "pageNum当前页不能为空")
    private Integer pageNum;

    /**
     * 页大小
     */
    @NotBlank(message = "pageSize页大小不能为空")
    private Integer pageSize;

    /**
     * 指定排序字段
     */
    private String orderBy;
}
