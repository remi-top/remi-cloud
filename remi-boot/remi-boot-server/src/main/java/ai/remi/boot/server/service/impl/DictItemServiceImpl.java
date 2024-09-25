package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.DictItem;
import ai.remi.boot.infra.mapper.DictItemMapper;
import ai.remi.boot.server.service.DictItemService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 字典项表(DictItem)服务实现层
 */
@Service("dictItemService")
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {

}

