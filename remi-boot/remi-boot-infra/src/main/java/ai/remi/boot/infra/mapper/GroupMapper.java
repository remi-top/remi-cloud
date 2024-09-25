package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.Group;
import ai.remi.boot.domain.tree.GroupTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 业务组织(Group)数据层
 */
@Mapper
public interface GroupMapper extends BaseMapper<Group> {

    @Select("<script> " +
            "select " +
            " g.*,  " +
            " ag.app_id,  " +
            " ag.app_code  " +
            "from " +
            " iam_group g, " +
            " iam_app_group ag  " +
            "where " +
            " g.group_code = ag.group_code  " +
            " AND g.id = ag.group_id  " +
            " and ag.app_id =  #{id}" +
            " <if test=\"status != null and status != '' \" >" +
            " and g.status = #{status}  " +
            " </if>" +
            " ORDER BY ISNULL( sort ) ASC,sort ASC" +
            "</script>")
    List<GroupTree> selectGroupByAppId(@Param("id") String id, @Param("status") Integer status);
}
