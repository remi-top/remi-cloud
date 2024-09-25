package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.DeptUser;
import ai.remi.boot.infra.mapper.DeptUserMapper;
import ai.remi.boot.server.service.DeptUserService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 部门用户表(DeptUser)服务实现层
 */
@Service("deptUserService")
public class DeptUserServiceImpl extends ServiceImpl<DeptUserMapper, DeptUser> implements DeptUserService {

}

