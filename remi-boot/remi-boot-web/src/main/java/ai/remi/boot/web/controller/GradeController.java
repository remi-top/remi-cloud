package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.GradeConverter;
import ai.remi.boot.domain.dto.post.GradePostDTO;
import ai.remi.boot.domain.dto.put.GradePutDTO;
import ai.remi.boot.domain.entity.Grade;
import ai.remi.boot.domain.query.GradeQuery;
import ai.remi.boot.domain.vo.GradeVO;
import ai.remi.boot.server.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 职系表(Grade)控制层
 */
@Validated
@RestController
@Tag(name = "职系管理")
@RequestMapping("grade")
public class GradeController {
    /**
     * 服务对象
     */
    @Resource
    private GradeService gradeService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<GradeVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Grade grade = gradeService.getById(id);
        //处理格式转换
        GradeVO gradeVO = GradeConverter.INSTANT.entityToVO(grade);
        return ResultBean.success(gradeVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param gradeQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<GradeVO>> list(@RequestBody GradeQuery gradeQuery) {
        //处理格式转换
        Grade grade = GradeConverter.INSTANT.queryToEntity(gradeQuery);
        //执行分页查询
        List<Grade> listResult = gradeService.list(new QueryWrapper<>(grade));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, GradeVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery  分页对象
     * @param gradeQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<GradeVO>> page(PageQuery pageQuery, GradeQuery gradeQuery) {
        //处理分页条件
        Page<Grade> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Grade grade = GradeConverter.INSTANT.queryToEntity(gradeQuery);
        //执行分页查询
        Page<Grade> pageResult = gradeService.page(page, new QueryWrapper<>(grade));
        PagerBean<GradeVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), GradeVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param gradeDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增职系信息", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated GradePostDTO gradeDTO) {
        //处理格式转换
        Grade grade = GradeConverter.INSTANT.postDtoToEntity(gradeDTO);
        //执行数据保存
        return ResultBean.success(gradeService.save(grade));
    }

    /**
     * 修改数据
     *
     * @param gradeDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "修改职系信息", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated GradePutDTO gradeDTO) {
        //处理格式转换
        Grade grade = GradeConverter.INSTANT.putDtoToEntity(gradeDTO);
        //执行数据更新
        return ResultBean.success(gradeService.updateById(grade));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除职系信息", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(gradeService.removeByIds(ids));
    }

}

