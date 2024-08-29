package ai.remi.sys.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.sys.domain.entity.RestrictIp;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 黑白名单表(RestrictIp)数据层
 */
@Mapper
public interface RestrictIpMapper extends BaseMapper<RestrictIp> {

}
