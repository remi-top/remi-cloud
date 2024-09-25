package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.AppPublishConverter;
import ai.remi.boot.domain.dto.post.AppPublishPostDTO;
import ai.remi.boot.domain.dto.put.AppPublishPutDTO;
import ai.remi.boot.domain.entity.AppPublish;
import ai.remi.boot.domain.query.AppPublishQuery;
import ai.remi.boot.domain.vo.AppPublishVO;
import ai.remi.boot.server.service.AppPublishService;
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
 * @desc 应用发布表(AppPublish)控制层
 */
@Validated
@RestController
@Tag(name = "应用发布")
@RequestMapping("appPublish")
public class AppPublishController {
    /**
     * 服务对象
     */
    @Resource
    private AppPublishService appPublishService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<AppPublishVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        AppPublish appPublish = appPublishService.getById(id);
        //处理格式转换
        AppPublishVO appPublishVO = AppPublishConverter.INSTANT.entityToVO(appPublish);
        return ResultBean.success(appPublishVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param appPublishQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<AppPublishVO>> list(@RequestBody AppPublishQuery appPublishQuery) {
        //处理格式转换
        AppPublish appPublish = AppPublishConverter.INSTANT.queryToEntity(appPublishQuery);
        //执行分页查询
        List<AppPublish> listResult = appPublishService.list(new QueryWrapper<>(appPublish));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, AppPublishVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery       分页对象
     * @param appPublishQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<AppPublishVO>> page(PageQuery pageQuery, AppPublishQuery appPublishQuery) {
        //处理分页条件
        Page<AppPublish> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        AppPublish appPublish = AppPublishConverter.INSTANT.queryToEntity(appPublishQuery);
        //执行分页查询
        Page<AppPublish> pageResult = appPublishService.page(page, new QueryWrapper<>(appPublish));
        PagerBean<AppPublishVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), AppPublishVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param appPublishDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    public ResultBean<Boolean> insert(@RequestBody @Validated AppPublishPostDTO appPublishDTO) {
        //处理格式转换
        AppPublish appPublish = AppPublishConverter.INSTANT.postDtoToEntity(appPublishDTO);
        //执行数据保存
        return ResultBean.success(appPublishService.save(appPublish));
    }

    /**
     * 修改数据
     *
     * @param appPublishDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated AppPublishPutDTO appPublishDTO) {
        //处理格式转换
        AppPublish appPublish = AppPublishConverter.INSTANT.putDtoToEntity(appPublishDTO);
        //执行数据更新
        return ResultBean.success(appPublishService.updateById(appPublish));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(appPublishService.removeByIds(ids));
    }

}

