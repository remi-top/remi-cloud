package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.tree.TreeBuild;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.string.StringUtils;
import ai.remi.boot.domain.converter.AppMenuConverter;
import ai.remi.boot.domain.dto.post.AppMenuPostDTO;
import ai.remi.boot.domain.entity.App;
import ai.remi.boot.domain.entity.AppMenu;
import ai.remi.boot.domain.query.AppMenuQuery;
import ai.remi.boot.domain.tree.MenuTree;
import ai.remi.boot.domain.vo.AppMenuVO;
import ai.remi.boot.infra.mapper.MenuMapper;
import ai.remi.boot.server.service.AppMenuService;
import ai.remi.boot.server.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用菜单(AppMenu)控制层
 */
@Validated
@RestController
@Tag(name = "应用菜单")
@RequestMapping("appMenu")
public class AppMenuController {
    /**
     * 服务对象
     */
    @Resource
    private AppMenuService appMenuService;
    @Resource
    private AppService appService;
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
    public ResultBean<AppMenuVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        AppMenu appMenu = appMenuService.getById(id);
        //处理格式转换
        AppMenuVO appMenuVO = AppMenuConverter.INSTANT.entityToVO(appMenu);
        return ResultBean.success(appMenuVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param appMenuQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/tree")
    @Operation(summary = "查询树结构")
    public ResultBean<List<AppMenuVO>> tree(@RequestBody AppMenuQuery appMenuQuery) {
        List<AppMenuVO> appMenuVOS = new ArrayList<>();
        List<App> apps = appService.list(Wrappers.<App>lambdaQuery().eq(StringUtils.isNoneBlank(appMenuQuery.getAppId()), App::getId, appMenuQuery.getAppId()).eq(StringUtils.isNoneBlank(appMenuQuery.getAppCode()), App::getAppCode, appMenuQuery.getAppCode()).eq(App::getStatus, 1));
        for (App app : apps) {
            List<MenuTree> menuTrees = menuMapper.selectMenuByAppId(app.getId(), 1);
            //查询条件为空，默认查询根结点为0的一级部门
            if (CollectionUtils.isNotEmpty(menuTrees)) {
                String rootId = "0";
                // 创建树形结构并返回
                TreeBuild treeBuild = new TreeBuild(rootId, menuTrees);
                appMenuVOS.add(AppMenuVO.builder().appId(app.getId()).appCode(app.getAppCode()).appName(app.getAppName()).appNameEn(app.getAppNameEn()).menuTree(treeBuild.buildTree()).build());
            }
        }
        return ResultBean.success(appMenuVOS);
    }

    /**
     * 给应用批量配置菜单
     *
     * @param appMenuDTOs 实体对象
     * @return 新增结果
     */
    @PostMapping("/addOrUpdate")
    @Operation(summary = "给应用批量配置菜单")
    //@LogRecord(content = "给应用批量配置菜单", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> addOrUpdate(@RequestBody @Validated List<AppMenuPostDTO> appMenuDTOs) {
        //先删除后新增
        appMenuService.remove(Wrappers.<AppMenu>lambdaQuery().eq(AppMenu::getAppId, appMenuDTOs.get(0).getAppId()));
        //执行数据保存
        appMenuService.saveBatch(BeanCopyUtils.copyListProperties(appMenuDTOs, AppMenu.class));
        return ResultBean.success();
    }
}

