package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.boot.domain.entity.Menu;
import ai.remi.boot.infra.mapper.MenuMapper;
import ai.remi.boot.server.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 菜单表(Menu)服务实现层
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}

