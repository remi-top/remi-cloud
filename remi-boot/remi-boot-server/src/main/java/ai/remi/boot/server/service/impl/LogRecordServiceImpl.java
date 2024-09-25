package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.LogRecord;
import ai.remi.boot.infra.mapper.LogRecordMapper;
import ai.remi.boot.server.service.LogRecordService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 日志记录表(LogRecord)服务实现层
 */
@Service("logRecordService")
public class LogRecordServiceImpl extends ServiceImpl<LogRecordMapper, LogRecord> implements LogRecordService {

}

