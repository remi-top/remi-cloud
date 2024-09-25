package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.UserPost;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户岗位表(UserPost)数据层
 */
@Mapper
public interface UserPostMapper extends BaseMapper<UserPost> {

}
