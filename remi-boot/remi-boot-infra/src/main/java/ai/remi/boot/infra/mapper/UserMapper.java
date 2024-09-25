package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.User;
import ai.remi.boot.domain.query.UserQuery;
import ai.remi.boot.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户表(User)数据层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT " +
            " u.*, " +
            " up.position_id, " +
            " up.position_code, " +
            " up.rank_id, " +
            " up.rank_code,  " +
            " du.dept_id, " +
            " du.dept_code, " +
            " cd.company_id, " +
            " cd.company_code  " +
            "FROM " +
            " iam_user u, " +
            " iam_user_post up, " +
            " iam_dept_user du, " +
            " iam_company_dept cd  " +
            "WHERE " +
            " u.id = du.user_id  " +
            " and u.user_code = du.user_code " +
            " and u.id = up.user_id  " +
            " and u.user_code = up.user_code  " +
            " and du.dept_id = cd.dept_id  " +
            " and du.dept_code = cd.dept_code  " +
            " and u.id = #{id}")
    UserVO selectOneById(String id);

    @Select("<script>" +
            "select " +
            " u.*, " +
            " du.dept_id, " +
            " du.dept_code, " +
            " cd.company_id, " +
            " cd.company_code  " +
            "from " +
            " iam_user u, " +
            " iam_dept_user du, " +
            " iam_company_dept cd  " +
            "where " +
            " u.id = du.user_id  " +
            " and u.user_code = du.user_code  " +
            " and du.dept_id = cd.dept_id  " +
            " and du.dept_code = cd.dept_code  " +
            " <if test=\"userQuery.companyId != null and userQuery.companyId != '' \" >" +
            " and cd.company_id = #{userQuery.companyId}  " +
            " </if>" +
            " <if test=\"userQuery.companyCode != null and userQuery.companyCode != '' \" >" +
            " and cd.company_code = #{userQuery.companyCode}  " +
            " </if>" +
            " <if test=\"userQuery.deptId != null and userQuery.deptId != '' \" >" +
            " and cd.dept_id = #{userQuery.deptId}  " +
            " </if>" +
            " <if test=\"userQuery.deptCode != null and userQuery.deptCode != '' \" >" +
            " and cd.dept_code = #{userQuery.deptCode}  " +
            " </if>" +
            "</script>")
    List<UserVO> selectListByUser(@Param("userQuery") UserQuery userQuery);

    @Select("<script>" +
            "select " +
            " u.*, " +
            " up.position_id, " +
            " up.position_code, " +
            " up.rank_id, " +
            " up.rank_code,  " +
            " du.dept_id, " +
            " du.dept_code, " +
            " cd.company_id, " +
            " cd.company_code  " +
            "from " +
            " iam_user u, " +
            " iam_user_post up, " +
            " iam_dept_user du, " +
            " iam_company_dept cd  " +
            "where " +
            " u.id = du.user_id  " +
            " and u.user_code = du.user_code  " +
            " and u.id = up.user_id  " +
            " and u.user_code = up.user_code  " +
            " and du.dept_id = cd.dept_id  " +
            " and du.dept_code = cd.dept_code  " +
            " <if test=\"userQuery.companyId != null and userQuery.companyId != '' \" >" +
            " and cd.company_id = #{userQuery.companyId}  " +
            " </if>" +
            " <if test=\"userQuery.companyCode != null and userQuery.companyCode != '' \" >" +
            " and cd.company_code = #{userQuery.companyCode}  " +
            " </if>" +
            " <if test=\"userQuery.deptId != null and userQuery.deptId != '' \" >" +
            " and cd.dept_id = #{userQuery.deptId}  " +
            " </if>" +
            " <if test=\"userQuery.deptCode != null and userQuery.deptCode != '' \" >" +
            " and cd.dept_code = #{userQuery.deptCode}  " +
            " </if>" +
            " limit #{pageNum},#{pageSize}" +
            "</script>")
    List<UserVO> selectPageByUser(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("userQuery") UserQuery userQuery);

    @Select("<script>" +
            "select " +
            " count(u.id) as total " +
            "from " +
            " iam_user u, " +
            " iam_dept_user du, " +
            " iam_company_dept cd  " +
            "where " +
            " u.id = du.user_id  " +
            " and u.user_code = du.user_code  " +
            " and du.dept_id = cd.dept_id  " +
            " and du.dept_code = cd.dept_code  " +
            " <if test=\"userQuery.companyId != null and userQuery.companyId != '' \" >" +
            " and cd.company_id = #{userQuery.companyId}  " +
            " </if>" +
            " <if test=\"userQuery.companyCode != null and userQuery.companyCode != '' \" >" +
            " and cd.company_code = #{userQuery.companyCode}  " +
            " </if>" +
            " <if test=\"userQuery.deptId != null and userQuery.deptId != '' \" >" +
            " and cd.dept_id = #{userQuery.deptId}  " +
            " </if>" +
            " <if test=\"userQuery.deptCode != null and userQuery.deptCode != '' \" >" +
            " and cd.dept_code = #{userQuery.deptCode}  " +
            " </if>" +
            "</script>")
    Long selectCountByUser(@Param("userQuery") UserQuery userQuery);

}
