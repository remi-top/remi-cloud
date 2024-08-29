package ai.remi.sys.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.sys.domain.entity.GroupUser;
import ai.remi.sys.infra.mapper.GroupUserMapper;
import ai.remi.sys.server.service.GroupUserService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 组织用户(GroupUser)服务实现层
 */
@Service("groupUserService")
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements GroupUserService {

}

