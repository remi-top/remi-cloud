package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.RankConverter;
import ai.remi.boot.domain.dto.post.RankPostDTO;
import ai.remi.boot.domain.dto.put.RankPutDTO;
import ai.remi.boot.domain.entity.Rank;
import ai.remi.boot.domain.query.RankQuery;
import ai.remi.boot.domain.vo.RankVO;
import ai.remi.boot.server.service.RankService;
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
 * @desc 职级表(Rank)控制层
 */
@Validated
@RestController
@Tag(name = "职级管理")
@RequestMapping("rank")
public class RankController {
    /**
     * 服务对象
     */
    @Resource
    private RankService rankService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<RankVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Rank rank = rankService.getById(id);
        //处理格式转换
        RankVO rankVO = RankConverter.INSTANT.entityToVO(rank);
        return ResultBean.success(rankVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param rankQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<RankVO>> list(@RequestBody RankQuery rankQuery) {
        //处理格式转换
        Rank rank = RankConverter.INSTANT.queryToEntity(rankQuery);
        //执行分页查询
        List<Rank> listResult = rankService.list(new QueryWrapper<>(rank));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, RankVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param rankQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<RankVO>> page(PageQuery pageQuery, RankQuery rankQuery) {
        //处理分页条件
        Page<Rank> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Rank rank = RankConverter.INSTANT.queryToEntity(rankQuery);
        //执行分页查询
        Page<Rank> pageResult = rankService.page(page, new QueryWrapper<>(rank));
        PagerBean<RankVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), RankVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param rankDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增职级信息", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated RankPostDTO rankDTO) {
        //处理格式转换
        Rank rank = RankConverter.INSTANT.postDtoToEntity(rankDTO);
        //执行数据保存
        return ResultBean.success(rankService.save(rank));
    }

    /**
     * 修改数据
     *
     * @param rankDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "修改职级信息", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated RankPutDTO rankDTO) {
        //处理格式转换
        Rank rank = RankConverter.INSTANT.putDtoToEntity(rankDTO);
        //执行数据更新
        return ResultBean.success(rankService.updateById(rank));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除职级信息", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(rankService.removeByIds(ids));
    }

}

