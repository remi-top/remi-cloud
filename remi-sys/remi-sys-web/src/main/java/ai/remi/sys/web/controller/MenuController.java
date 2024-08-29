package ai.remi.sys.web.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.tree.TreeBuild;
import ai.remi.comm.exception.util.MessageUtils;
import ai.remi.comm.log.annotation.LogRecord;
import ai.remi.comm.log.enums.BusinessType;
import ai.remi.comm.util.asserts.AssertUtils;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.id.SnowflakeUtils;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.sys.domain.converter.MenuConverter;
import ai.remi.sys.domain.dto.post.MenuPostDTO;
import ai.remi.sys.domain.dto.put.MenuPutDTO;
import ai.remi.sys.domain.entity.AppMenu;
import ai.remi.sys.domain.entity.Menu;
import ai.remi.sys.domain.query.MenuQuery;
import ai.remi.sys.domain.tree.MenuTree;
import ai.remi.sys.domain.vo.MenuVO;
import ai.remi.sys.infra.mapper.MenuMapper;
import ai.remi.sys.server.service.AppMenuService;
import ai.remi.sys.server.service.MenuService;
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
 * @desc 菜单表(Menu)控制层
 */
@Validated
@RestController
@Tag(name = "菜单管理")
@RequestMapping("menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private AppMenuService appMenuService;

    @Resource
    private MenuService menuService;

    @Resource
    private MenuMapper menuMapper;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<MenuVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        MenuVO menuVO = menuMapper.selectOneById(id);
        return ResultBean.success(menuVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param menuQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/tree")
    @Operation(summary = "查询树结构")
    public ResultBean<List<MenuTree>> tree(@RequestBody MenuQuery menuQuery) {
        List<MenuTree> menuTrees = menuMapper.selectMenuByAppId(menuQuery.getAppId(), menuQuery.getStatus());
        //查询条件为空，默认查询根结点为0的一级部门
        if (CollectionUtils.isNotEmpty(menuTrees)) {
            String rootId = "0";
            // 创建树形结构并返回
            TreeBuild treeBuild = new TreeBuild(rootId, menuTrees);
            return ResultBean.success(treeBuild.buildTree());
        }
        return ResultBean.success(menuTrees);
    }

    /**
     * 新增数据
     *
     * @param menuDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    @LogRecord(content = "新增菜单数据", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated MenuPostDTO menuDTO) {
        //处理格式转换
        Menu menu = MenuConverter.INSTANT.postDtoToEntity(menuDTO);
        menu.setId(SnowflakeUtils.getInstance().nextIdStr());
        //保存应用菜单中间表
        AppMenu one = appMenuService.getOne(Wrappers.lambdaQuery(AppMenu.class).eq(AppMenu::getAppId, menuDTO.getAppId()).eq(AppMenu::getMenuCode, menuDTO.getMenuCode()).last("limit 1"));
        //应用下已存在相同菜单
        AssertUtils.isTrue(ObjectUtils.isEmpty(one), MessageUtils.getMessage("add.menu.error"));
        AppMenu appMenu = AppMenu.builder()
                .appId(menuDTO.getAppId()).appCode(menuDTO.getAppCode())
                .menuId(menu.getId()).menuCode(menu.getMenuCode()).build();
        appMenuService.save(appMenu);
        //执行数据保存
        return ResultBean.success(menuService.save(menu));
    }

    /**
     * 修改数据
     *
     * @param menuDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    @LogRecord(content = "修改菜单数据", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated MenuPutDTO menuDTO) {
        //处理格式转换
        Menu menu = MenuConverter.INSTANT.putDtoToEntity(menuDTO);
        //执行数据更新
        return ResultBean.success(menuService.updateById(menu));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    @LogRecord(content = "删除菜单数据", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        for (String id : ids) {
            //删除应用菜单中间表
            appMenuService.remove(Wrappers.<AppMenu>lambdaQuery().eq(AppMenu::getMenuId, id));
        }
        return ResultBean.success(menuService.removeByIds(ids));
    }

}

