package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.Position;
import ai.remi.boot.infra.mapper.PositionMapper;
import ai.remi.boot.server.service.PositionService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 职位表(Position)服务实现层
 */
@Service("positionService")
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

}

