package ai.remi.sys.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.sys.domain.entity.AppGroup;
import ai.remi.sys.infra.mapper.AppGroupMapper;
import ai.remi.sys.server.service.AppGroupService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用组织(AppGroup)服务实现层
 */
@Service("appGroupService")
public class AppGroupServiceImpl extends ServiceImpl<AppGroupMapper, AppGroup> implements AppGroupService {

}

