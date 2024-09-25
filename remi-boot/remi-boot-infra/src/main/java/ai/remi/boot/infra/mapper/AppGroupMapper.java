package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.AppGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用组织(AppGroup)数据层
 */
@Mapper
public interface AppGroupMapper extends BaseMapper<AppGroup> {

}
