package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.App;
import ai.remi.boot.infra.mapper.AppMapper;
import ai.remi.boot.server.service.AppService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用表Application(App)服务实现层
 */
@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements AppService {

}

