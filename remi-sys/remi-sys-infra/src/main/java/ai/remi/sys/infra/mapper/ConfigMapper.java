package ai.remi.sys.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.sys.domain.entity.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 全局配置(Config)数据层
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

}