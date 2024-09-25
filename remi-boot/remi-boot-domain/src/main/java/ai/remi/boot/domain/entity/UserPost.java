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
 * @desc 用户岗位 UserPostDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_user_post")
public class UserPost extends BaseEntity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 职位ID
     */
    private String positionId;

    /**
     * 职位编码
     */
    private String positionCode;

    /**
     * 职级ID
     */
    private String rankId;

    /**
     * 职级编码
     */
    private String rankCode;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
