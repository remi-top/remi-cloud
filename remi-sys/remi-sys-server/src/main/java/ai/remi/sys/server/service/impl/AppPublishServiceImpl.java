package ai.remi.sys.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.sys.domain.entity.AppPublish;
import ai.remi.sys.infra.mapper.AppPublishMapper;
import ai.remi.sys.server.service.AppPublishService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用发布表(AppPublish)服务实现层
 */
@Service("appPublishService")
public class AppPublishServiceImpl extends ServiceImpl<AppPublishMapper, AppPublish> implements AppPublishService {

}

