package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.DictConverter;
import ai.remi.boot.domain.dto.post.DictPostDTO;
import ai.remi.boot.domain.dto.put.DictPutDTO;
import ai.remi.boot.domain.entity.Dict;
import ai.remi.boot.domain.query.DictQuery;
import ai.remi.boot.domain.vo.DictVO;
import ai.remi.boot.server.service.DictService;
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
 * @desc 字典主表(Dict)控制层
 */
@Validated
@RestController
@Tag(name = "字典管理")
@RequestMapping("dict")
public class DictController {
    /**
     * 服务对象
     */
    @Resource
    private DictService dictService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<DictVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Dict dict = dictService.getById(id);
        //处理格式转换
        DictVO dictVO = DictConverter.INSTANT.entityToVO(dict);
        return ResultBean.success(dictVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param dictQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<DictVO>> list(@RequestBody DictQuery dictQuery) {
        //处理格式转换
        Dict dict = DictConverter.INSTANT.queryToEntity(dictQuery);
        //执行分页查询
        List<Dict> listResult = dictService.list(new QueryWrapper<>(dict));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, DictVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param dictQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<DictVO>> page(PageQuery pageQuery, DictQuery dictQuery) {
        //处理分页条件
        Page<Dict> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Dict dict = DictConverter.INSTANT.queryToEntity(dictQuery);
        //执行分页查询
        Page<Dict> pageResult = dictService.page(page, new QueryWrapper<>(dict));
        PagerBean<DictVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), DictVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param dictDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增字典数据", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated DictPostDTO dictDTO) {
        //处理格式转换
        Dict dict = DictConverter.INSTANT.postDtoToEntity(dictDTO);
        //执行数据保存
        return ResultBean.success(dictService.save(dict));
    }

    /**
     * 修改数据
     *
     * @param dictDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "修改字典数据", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated DictPutDTO dictDTO) {
        //处理格式转换
        Dict dict = DictConverter.INSTANT.putDtoToEntity(dictDTO);
        //执行数据更新
        return ResultBean.success(dictService.updateById(dict));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除字典树", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(dictService.removeByIds(ids));
    }

}

