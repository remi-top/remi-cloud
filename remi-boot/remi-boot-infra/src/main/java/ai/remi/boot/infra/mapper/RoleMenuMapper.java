package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 角色菜单表(RoleMenu)数据层
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}
