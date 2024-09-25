package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.DictItemConverter;
import ai.remi.boot.domain.dto.post.DictItemPostDTO;
import ai.remi.boot.domain.dto.put.DictItemPutDTO;
import ai.remi.boot.domain.entity.DictItem;
import ai.remi.boot.domain.query.DictItemQuery;
import ai.remi.boot.domain.vo.DictItemVO;
import ai.remi.boot.server.service.DictItemService;
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
 * @desc 字典项表(DictItem)控制层
 */
@Validated
@RestController
@Tag(name = "字典项值")
@RequestMapping("dictItem")
public class DictItemController {
    /**
     * 服务对象
     */
    @Resource
    private DictItemService dictItemService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<DictItemVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        DictItem dictItem = dictItemService.getById(id);
        //处理格式转换
        DictItemVO dictItemVO = DictItemConverter.INSTANT.entityToVO(dictItem);
        return ResultBean.success(dictItemVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param dictItemQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<DictItemVO>> list(@RequestBody DictItemQuery dictItemQuery) {
        //处理格式转换
        DictItem dictItem = DictItemConverter.INSTANT.queryToEntity(dictItemQuery);
        //执行分页查询
        List<DictItem> listResult = dictItemService.list(new QueryWrapper<>(dictItem));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, DictItemVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery     分页对象
     * @param dictItemQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<DictItemVO>> page(PageQuery pageQuery, DictItemQuery dictItemQuery) {
        //处理分页条件
        Page<DictItem> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        DictItem dictItem = DictItemConverter.INSTANT.queryToEntity(dictItemQuery);
        //执行分页查询
        Page<DictItem> pageResult = dictItemService.page(page, new QueryWrapper<>(dictItem));
        PagerBean<DictItemVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), DictItemVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param dictItemDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增字典项值", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated DictItemPostDTO dictItemDTO) {
        //处理格式转换
        DictItem dictItem = DictItemConverter.INSTANT.postDtoToEntity(dictItemDTO);
        //执行数据保存
        return ResultBean.success(dictItemService.save(dictItem));
    }

    /**
     * 修改数据
     *
     * @param dictItemDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "修改字典项值", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated DictItemPutDTO dictItemDTO) {
        //处理格式转换
        DictItem dictItem = DictItemConverter.INSTANT.putDtoToEntity(dictItemDTO);
        //执行数据更新
        return ResultBean.success(dictItemService.updateById(dictItem));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除字典项值", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(dictItemService.removeByIds(ids));
    }

}

