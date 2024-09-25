package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.RoleConverter;
import ai.remi.boot.domain.dto.post.RolePostDTO;
import ai.remi.boot.domain.dto.put.RolePutDTO;
import ai.remi.boot.domain.entity.Role;
import ai.remi.boot.domain.query.RoleQuery;
import ai.remi.boot.domain.vo.RoleVO;
import ai.remi.boot.server.service.RoleService;
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
 * @desc 角色表(Role)控制层
 */
@Validated
@RestController
@Tag(name = "基础角色")
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<RoleVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Role role = roleService.getById(id);
        //处理格式转换
        RoleVO roleVO = RoleConverter.INSTANT.entityToVO(role);
        return ResultBean.success(roleVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param roleQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<RoleVO>> list(@RequestBody RoleQuery roleQuery) {
        //处理格式转换
        Role role = RoleConverter.INSTANT.queryToEntity(roleQuery);
        //执行分页查询
        List<Role> listResult = roleService.list(new QueryWrapper<>(role));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, RoleVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param roleQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<RoleVO>> page(PageQuery pageQuery, RoleQuery roleQuery) {
        //处理分页条件
        Page<Role> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Role role = RoleConverter.INSTANT.queryToEntity(roleQuery);
        //执行分页查询
        Page<Role> pageResult = roleService.page(page, new QueryWrapper<>(role));
        PagerBean<RoleVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), RoleVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param roleDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增角色信息", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated RolePostDTO roleDTO) {
        //处理格式转换
        Role role = RoleConverter.INSTANT.postDtoToEntity(roleDTO);
        //执行数据保存
        return ResultBean.success(roleService.save(role));
    }

    /**
     * 修改数据
     *
     * @param roleDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "更新角色信息", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated RolePutDTO roleDTO) {
        //处理格式转换
        Role role = RoleConverter.INSTANT.putDtoToEntity(roleDTO);
        //执行数据更新
        return ResultBean.success(roleService.updateById(role));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除角色信息", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(roleService.removeByIds(ids));
    }

}

