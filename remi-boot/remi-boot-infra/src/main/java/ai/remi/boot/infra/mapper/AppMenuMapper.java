package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.AppMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用菜单表(AppMenu)数据层
 */
@Mapper
public interface AppMenuMapper extends BaseMapper<AppMenu> {

}
