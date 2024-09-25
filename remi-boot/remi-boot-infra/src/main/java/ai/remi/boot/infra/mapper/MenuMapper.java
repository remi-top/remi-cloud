package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.Menu;
import ai.remi.boot.domain.tree.MenuTree;
import ai.remi.boot.domain.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 菜单表(Menu)数据层
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT " +
            " m.*,  " +
            " am.app_id,  " +
            " am.app_code  " +
            "FROM " +
            " iam_menu m, " +
            " iam_app_menu am  " +
            "WHERE " +
            " m.menu_code = am.menu_code  " +
            " AND m.id = am.menu_id  " +
            " AND am.menu_id =  #{id}")
    MenuVO selectOneById(String id);

    @Select("<script> " +
            "select " +
            " m.*,  " +
            " am.app_id,  " +
            " am.app_code  " +
            "from " +
            " iam_menu m, " +
            " iam_app_menu am  " +
            "where " +
            " m.menu_code = am.menu_code  " +
            " AND m.id = am.menu_id  " +
            " and am.app_id =  #{id}" +
            " <if test=\"status != null and status != '' \" >" +
            " and m.status = #{status}  " +
            " </if>" +
            " ORDER BY ISNULL( sort ) ASC,sort ASC" +
            "</script>")
    List<MenuTree> selectMenuByAppId(@Param("id") String id, @Param("status") Integer status);

    @Select("<script> " +
            "select " +
            " m.*,  " +
            " am.app_id,  " +
            " am.app_code  " +
            "from " +
            " iam_menu m, " +
            " iam_app_menu am  " +
            "where " +
            " m.menu_code = am.menu_code  " +
            " AND m.id = am.menu_id  " +
            " AND m.id IN" +
            " <foreach collection='ids' item='id' index='index' open='(' separator=',' close=')'>" +
            "  #{id}" +
            " </foreach>"+
            " <if test=\"status != null and status != '' \" >"+
            " AND m.status = #{status}  " +
            " </if>" +
            " ORDER BY ISNULL( sort ) ASC,sort ASC" +
            "</script>")
    List<MenuTree> selectMenuInIds(@Param("ids") List<String> ids, @Param("status") Integer status);

}
