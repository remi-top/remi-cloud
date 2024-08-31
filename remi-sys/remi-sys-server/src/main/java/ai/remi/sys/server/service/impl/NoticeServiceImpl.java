package ai.remi.sys.server.service.impl;

import ai.remi.sys.domain.entity.Notice;
import ai.remi.sys.infra.mapper.NoticeMapper;
import ai.remi.sys.server.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 通知公告(Notice)服务实现层
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}

