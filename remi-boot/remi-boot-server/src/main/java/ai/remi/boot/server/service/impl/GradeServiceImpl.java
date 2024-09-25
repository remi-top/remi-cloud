package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.Grade;
import ai.remi.boot.infra.mapper.GradeMapper;
import ai.remi.boot.server.service.GradeService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 职系表(Grade)服务实现层
 */
@Service("gradeService")
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

}

