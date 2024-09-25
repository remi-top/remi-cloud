package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.AreaConverter;
import ai.remi.boot.domain.dto.post.AreaPostDTO;
import ai.remi.boot.domain.dto.put.AreaPutDTO;
import ai.remi.boot.domain.entity.Area;
import ai.remi.boot.domain.query.AreaQuery;
import ai.remi.boot.domain.vo.AreaVO;
import ai.remi.boot.server.service.AreaService;
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
 * @desc 省市县表(Area)控制层
 */
@Validated
@RestController
@Tag(name = "区域管理")
@RequestMapping("area")
public class AreaController {
    /**
     * 服务对象
     */
    @Resource
    private AreaService areaService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<AreaVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Area area = areaService.getById(id);
        //处理格式转换
        AreaVO areaVO = AreaConverter.INSTANT.entityToVO(area);
        return ResultBean.success(areaVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param areaQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<AreaVO>> list(@RequestBody AreaQuery areaQuery) {
        //处理格式转换
        Area area = AreaConverter.INSTANT.queryToEntity(areaQuery);
        //执行分页查询
        List<Area> listResult = areaService.list(new QueryWrapper<>(area));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, AreaVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param areaQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<AreaVO>> page(PageQuery pageQuery, AreaQuery areaQuery) {
        //处理分页条件
        Page<Area> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Area area = AreaConverter.INSTANT.queryToEntity(areaQuery);
        //执行分页查询
        Page<Area> pageResult = areaService.page(page, new QueryWrapper<>(area));
        PagerBean<AreaVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), AreaVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param areaDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "录入省市县信息", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated AreaPostDTO areaDTO) {
        //处理格式转换
        Area area = AreaConverter.INSTANT.postDtoToEntity(areaDTO);
        //执行数据保存
        return ResultBean.success(areaService.save(area));
    }

    /**
     * 修改数据
     *
     * @param areaDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "更新省市县信息", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated AreaPutDTO areaDTO) {
        //处理格式转换
        Area area = AreaConverter.INSTANT.putDtoToEntity(areaDTO);
        //执行数据更新
        return ResultBean.success(areaService.updateById(area));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除省市县信息", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(areaService.removeByIds(ids));
    }

}

