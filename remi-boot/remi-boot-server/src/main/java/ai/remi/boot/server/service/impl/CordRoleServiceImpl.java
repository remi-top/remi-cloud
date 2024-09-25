package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.CordRole;
import ai.remi.boot.infra.mapper.CordRoleMapper;
import ai.remi.boot.server.service.CordRoleService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 组织角色表(CordRole)服务实现层
 */
@Service("deptRoleService")
public class CordRoleServiceImpl extends ServiceImpl<CordRoleMapper, CordRole> implements CordRoleService {

}

