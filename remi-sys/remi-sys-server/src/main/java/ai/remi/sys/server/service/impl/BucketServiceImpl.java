package ai.remi.sys.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.sys.domain.entity.Bucket;
import ai.remi.sys.infra.mapper.BucketMapper;
import ai.remi.sys.server.service.BucketService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 存储桶管理(Bucket)服务实现层
 */
@Service("bucketService")
public class BucketServiceImpl extends ServiceImpl<BucketMapper, Bucket> implements BucketService {

}

