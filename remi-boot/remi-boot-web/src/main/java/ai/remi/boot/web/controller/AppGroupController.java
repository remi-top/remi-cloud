package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.tree.TreeBuild;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.string.StringUtils;
import ai.remi.boot.domain.converter.AppGroupConverter;
import ai.remi.boot.domain.dto.post.AppGroupPostDTO;
import ai.remi.boot.domain.entity.AppGroup;
import ai.remi.boot.domain.entity.Group;
import ai.remi.boot.domain.query.AppGroupQuery;
import ai.remi.boot.domain.tree.GroupTree;
import ai.remi.boot.domain.vo.AppGroupVO;
import ai.remi.boot.server.service.AppGroupService;
import ai.remi.boot.server.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用组织(AppGroup)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Tag(name = "应用组织")
@RequestMapping("appGroup")
public class AppGroupController {
    /**
     * 服务对象
     */
    @Resource
    private AppGroupService appGroupService;
    @Resource
    private GroupService groupService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<AppGroupVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        AppGroup appGroup = appGroupService.getById(id);
        //处理格式转换
        AppGroupVO appGroupVO = AppGroupConverter.INSTANT.entityToVO(appGroup);
        return ResultBean.success(appGroupVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param appGroupQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/tree")
    @Operation(summary = "查询树结构")
    public ResultBean<List<GroupTree>> tree(@RequestBody AppGroupQuery appGroupQuery) {
        //先查询应用下的组织
        List<AppGroup> appGroups = appGroupService.list(Wrappers.<AppGroup>lambdaQuery().eq(StringUtils.isNoneBlank(appGroupQuery.getAppId()), AppGroup::getAppId, appGroupQuery.getAppId()).eq(StringUtils.isNoneBlank(appGroupQuery.getAppCode()), AppGroup::getAppCode, appGroupQuery.getAppCode()));
        // 使用Stream流获取每个人的年龄并收集到一个列表中
        List<String> groupIds = appGroups.stream().map(AppGroup::getGroupId).collect(Collectors.toList());
        //
        List<Group> listResult = groupService.getBaseMapper().selectList(Wrappers.<Group>lambdaQuery()
                .in(CollectionUtils.isNotEmpty(appGroups), Group::getId, groupIds)
                .last("ORDER BY ISNULL( sort ) ASC,sort ASC"));
        // 原查询结果转换树形结构
        List<GroupTree> groupTrees = BeanCopyUtils.coverList(listResult, GroupTree.class);
        if (CollectionUtils.isNotEmpty(groupTrees)) {
            String rootId = "0";
            // 创建树形结构并返回
            TreeBuild treeBuild = new TreeBuild(rootId, groupTrees);
            List buildTree = treeBuild.buildTree();
            return ResultBean.success(buildTree);
        }
        return ResultBean.success(groupTrees);
    }

    /**
     * 给应用批量配置菜单
     *
     * @param appGroups 实体对象
     * @return 新增结果
     */
    @PostMapping("/addOrUpdate")
    @Operation(summary = "给应用批量配置业务组织")
    //@LogRecord(content = "给应用批量配置业务组织", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> addOrUpdate(@RequestBody @Validated List<AppGroupPostDTO> appGroups) {
        //先删除后新增
        appGroupService.remove(Wrappers.<AppGroup>lambdaQuery().eq(AppGroup::getAppId, appGroups.get(0).getAppId()));
        //appGroupService
        appGroupService.saveBatch(BeanCopyUtils.copyListProperties(appGroups, AppGroup.class));
        return ResultBean.success();
    }

}

