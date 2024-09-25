package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.DictItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 字典项表(DictItem)数据层
 */
@Mapper
public interface DictItemMapper extends BaseMapper<DictItem> {

}
