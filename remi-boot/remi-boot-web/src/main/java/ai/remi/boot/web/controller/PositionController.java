package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.PositionConverter;
import ai.remi.boot.domain.dto.post.PositionPostDTO;
import ai.remi.boot.domain.dto.put.PositionPutDTO;
import ai.remi.boot.domain.entity.Position;
import ai.remi.boot.domain.query.PositionQuery;
import ai.remi.boot.domain.vo.PositionVO;
import ai.remi.boot.server.service.PositionService;
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
 * @desc 职位表(Position)控制层
 */
@Validated
@RestController
@Tag(name = "职位管理")
@RequestMapping("position")
public class PositionController {
    /**
     * 服务对象
     */
    @Resource
    private PositionService positionService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<PositionVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Position position = positionService.getById(id);
        //处理格式转换
        PositionVO positionVO = PositionConverter.INSTANT.entityToVO(position);
        return ResultBean.success(positionVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param positionQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<PositionVO>> list(@RequestBody PositionQuery positionQuery) {
        //处理格式转换
        Position position = PositionConverter.INSTANT.queryToEntity(positionQuery);
        //执行分页查询
        List<Position> listResult = positionService.list(new QueryWrapper<>(position));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, PositionVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery     分页对象
     * @param positionQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<PositionVO>> page(PageQuery pageQuery, PositionQuery positionQuery) {
        //处理分页条件
        Page<Position> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Position position = PositionConverter.INSTANT.queryToEntity(positionQuery);
        //执行分页查询
        Page<Position> pageResult = positionService.page(page, new QueryWrapper<>(position));
        PagerBean<PositionVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), PositionVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param positionDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增职位信息", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated PositionPostDTO positionDTO) {
        //处理格式转换
        Position position = PositionConverter.INSTANT.postDtoToEntity(positionDTO);
        //执行数据保存
        return ResultBean.success(positionService.save(position));
    }

    /**
     * 修改数据
     *
     * @param positionDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "修改职位信息", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated PositionPutDTO positionDTO) {
        //处理格式转换
        Position position = PositionConverter.INSTANT.putDtoToEntity(positionDTO);
        //执行数据更新
        return ResultBean.success(positionService.updateById(position));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除职位信息", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(positionService.removeByIds(ids));
    }

}

