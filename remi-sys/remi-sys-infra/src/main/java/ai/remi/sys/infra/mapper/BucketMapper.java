package ai.remi.sys.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.sys.domain.entity.Bucket;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 存储桶管理(Bucket)数据层
 */
@Mapper
public interface BucketMapper extends BaseMapper<Bucket> {

}