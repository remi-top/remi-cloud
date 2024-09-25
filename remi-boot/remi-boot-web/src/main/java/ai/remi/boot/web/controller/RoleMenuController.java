package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.RoleMenuConverter;
import ai.remi.boot.domain.dto.post.RoleMenuPostDTO;
import ai.remi.boot.domain.entity.Menu;
import ai.remi.boot.domain.entity.RoleMenu;
import ai.remi.boot.domain.query.RoleMenuQuery;
import ai.remi.boot.domain.vo.RoleMenuVO;
import ai.remi.boot.server.service.MenuService;
import ai.remi.boot.server.service.RoleMenuService;
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
 * @desc 角色菜单表(RoleMenu)控制层
 */
@Validated
@RestController
@Tag(name = "菜单权限")
@RequestMapping("roleMenu")
public class RoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private MenuService menuService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<RoleMenuVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        RoleMenu roleMenu = roleMenuService.getById(id);
        //处理格式转换
        RoleMenuVO roleMenuVO = RoleMenuConverter.INSTANT.entityToVO(roleMenu);
        return ResultBean.success(roleMenuVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param roleMenuQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<RoleMenuVO>> list(@RequestBody RoleMenuQuery roleMenuQuery) {
        //处理格式转换
        RoleMenu roleMenu = RoleMenuConverter.INSTANT.queryToEntity(roleMenuQuery);
        //执行分页查询
        List<RoleMenu> listResult = roleMenuService.list(new QueryWrapper<>(roleMenu));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, RoleMenuVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery     分页对象
     * @param roleMenuQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<RoleMenuVO>> page(PageQuery pageQuery, RoleMenuQuery roleMenuQuery) {
        //处理分页条件
        Page<RoleMenu> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        RoleMenu roleMenu = RoleMenuConverter.INSTANT.queryToEntity(roleMenuQuery);
        //执行分页查询
        Page<RoleMenu> pageResult = roleMenuService.page(page, new QueryWrapper<>(roleMenu));
        List<RoleMenuVO> roleMenuVOS = BeanCopyUtils.coverList(pageResult.getRecords(), RoleMenuVO.class);
        for (RoleMenuVO roleMenuVO : roleMenuVOS) {
            Menu menu = menuService.getById(roleMenuVO.getMenuId());
            roleMenuVO.setMenuName(menu.getMenuName());
            roleMenuVO.setMenuNameEn(menu.getMenuNameEn());
            roleMenuVO.setMenuType(menu.getMenuType());
        }
        PagerBean<RoleMenuVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), roleMenuVOS);
        return ResultBean.success(pageBean);
    }

    /**
     * 给角色批量授权菜单
     *
     * @param roleMenuDTOs 实体对象
     * @return 新增结果
     */
    @PostMapping("/addOrUpdate")
    @Operation(summary = "给角色批量授权菜单")
    //@LogRecord(content = "给角色批量授权菜单", businessType = BusinessType.GRANT)
    public ResultBean<Boolean> addOrUpdate(@RequestBody @Validated List<RoleMenuPostDTO> roleMenuDTOs) {
        //先删除后新增
        roleMenuService.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, roleMenuDTOs.get(0).getRoleId()));
        //执行数据保存
        roleMenuService.saveBatch(BeanCopyUtils.copyListProperties(roleMenuDTOs, RoleMenu.class));
        return ResultBean.success();
    }

}

