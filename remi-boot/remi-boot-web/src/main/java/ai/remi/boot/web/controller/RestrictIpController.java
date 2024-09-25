package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.RestrictIpConverter;
import ai.remi.boot.domain.dto.post.RestrictIpPostDTO;
import ai.remi.boot.domain.dto.put.RestrictIpPutDTO;
import ai.remi.boot.domain.entity.RestrictIp;
import ai.remi.boot.domain.query.RestrictIpQuery;
import ai.remi.boot.domain.vo.RestrictIpVO;
import ai.remi.boot.server.service.RestrictIpService;
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
 * @desc 黑白名单表(RestrictIp)控制层
 */
@Validated
@RestController
@Tag(name = "黑白名单")
@RequestMapping("restrictIp")
public class RestrictIpController {
    /**
     * 服务对象
     */
    @Resource
    private RestrictIpService restrictIpService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<RestrictIpVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        RestrictIp restrictIp = restrictIpService.getById(id);
        //处理格式转换
        RestrictIpVO restrictIpVO = RestrictIpConverter.INSTANT.entityToVO(restrictIp);
        return ResultBean.success(restrictIpVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param restrictIpQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<RestrictIpVO>> list(@RequestBody RestrictIpQuery restrictIpQuery) {
        //处理格式转换
        RestrictIp restrictIp = RestrictIpConverter.INSTANT.queryToEntity(restrictIpQuery);
        //执行分页查询
        List<RestrictIp> listResult = restrictIpService.list(new QueryWrapper<>(restrictIp));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, RestrictIpVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery       分页对象
     * @param restrictIpQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<RestrictIpVO>> page(PageQuery pageQuery, RestrictIpQuery restrictIpQuery) {
        //处理分页条件
        Page<RestrictIp> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        RestrictIp restrictIp = RestrictIpConverter.INSTANT.queryToEntity(restrictIpQuery);
        //执行分页查询
        Page<RestrictIp> pageResult = restrictIpService.page(page, new QueryWrapper<>(restrictIp));
        PagerBean<RestrictIpVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), RestrictIpVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param restrictIpDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "录入黑白名单", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated RestrictIpPostDTO restrictIpDTO) {
        //处理格式转换
        RestrictIp restrictIp = RestrictIpConverter.INSTANT.postDtoToEntity(restrictIpDTO);
        //执行数据保存
        return ResultBean.success(restrictIpService.save(restrictIp));
    }

    /**
     * 修改数据
     *
     * @param restrictIpDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "更新黑白名单", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated RestrictIpPutDTO restrictIpDTO) {
        //处理格式转换
        RestrictIp restrictIp = RestrictIpConverter.INSTANT.putDtoToEntity(restrictIpDTO);
        //执行数据更新
        return ResultBean.success(restrictIpService.updateById(restrictIp));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除黑白名单", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(restrictIpService.removeByIds(ids));
    }

}

