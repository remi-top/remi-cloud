package ai.remi.sys.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.sys.domain.entity.LogRecord;
import ai.remi.sys.infra.mapper.LogRecordMapper;
import ai.remi.sys.server.service.LogRecordService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 日志记录表(LogRecord)服务实现层
 */
@Service("logRecordService")
public class LogRecordServiceImpl extends ServiceImpl<LogRecordMapper, LogRecord> implements LogRecordService {

}

