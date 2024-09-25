package ai.remi.boot.infra.mapper;

import ai.remi.boot.domain.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 通知公告(Notice)数据层
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}
