package ai.remi.sys.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.sys.domain.entity.CompanyDept;
import ai.remi.sys.infra.mapper.CompanyDeptMapper;
import ai.remi.sys.server.service.CompanyDeptService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 公司部门表(CompanyDept)服务实现层
 */
@Service("companyDeptService")
public class CompanyDeptServiceImpl extends ServiceImpl<CompanyDeptMapper, CompanyDept> implements CompanyDeptService {

}

