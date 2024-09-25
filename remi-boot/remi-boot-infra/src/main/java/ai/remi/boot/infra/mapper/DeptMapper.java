package ai.remi.boot.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ai.remi.boot.domain.entity.Dept;
import ai.remi.boot.domain.tree.DeptTree;
import ai.remi.boot.domain.vo.DeptVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 部门表(Dept)数据层
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

    @Select("SELECT " +
        " d.*,  " +
        " cd.company_id,  " +
        " cd.company_code  " +
        "FROM " +
        " iam_dept d, " +
        " iam_company_dept cd  " +
        "WHERE " +
        " d.dept_code = cd.dept_code  " +
        " AND d.id = cd.dept_id  " +
        " AND cd.dept_id =  #{id}")
    DeptVO selectOneById(String id);

    @Select("<script> " +
        "select " +
        " d.*,  " +
        " cd.company_id,  " +
        " cd.company_code  " +
        "from " +
        " iam_dept d, " +
        " iam_company_dept cd  " +
        "where " +
        " d.dept_code = cd.dept_code  " +
        " and d.id = cd.dept_id  " +
        " and cd.company_id =  #{id}"+
        " <if test=\"status != null and status != '' \" >" +
        " and d.status = #{status}  " +
        " </if>" +
        " ORDER BY ISNULL( sort ) ASC,sort ASC" +
        "</script>")
    List<DeptTree> selectDeptByCompanyId(@Param("id")String id,@Param("status")Integer status);
}
