package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.Group;
import ai.remi.boot.infra.mapper.GroupMapper;
import ai.remi.boot.server.service.GroupService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 业务组织(Group)服务实现层
 */
@Service("groupService")
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

}

