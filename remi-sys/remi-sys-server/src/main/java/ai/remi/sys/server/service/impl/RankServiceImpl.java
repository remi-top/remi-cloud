package ai.remi.sys.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.sys.domain.entity.Rank;
import ai.remi.sys.infra.mapper.RankMapper;
import ai.remi.sys.server.service.RankService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 职级表(Rank)服务实现层
 */
@Service("rankService")
public class RankServiceImpl extends ServiceImpl<RankMapper, Rank> implements RankService {

}

