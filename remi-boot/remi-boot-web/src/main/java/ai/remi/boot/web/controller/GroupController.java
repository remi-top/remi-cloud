package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.domain.tree.TreeBuild;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.string.StringUtils;
import ai.remi.boot.domain.converter.GroupConverter;
import ai.remi.boot.domain.dto.post.GroupPostDTO;
import ai.remi.boot.domain.dto.put.GroupPutDTO;
import ai.remi.boot.domain.entity.Group;
import ai.remi.boot.domain.query.GroupQuery;
import ai.remi.boot.domain.tree.GroupTree;
import ai.remi.boot.domain.vo.GroupVO;
import ai.remi.boot.server.service.GroupService;
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
 * @desc 业务组织(Group)控制层
 */
@Validated
@RestController
@Tag(name = "业务组织")
@RequestMapping("group")
public class GroupController {
    /**
     * 服务对象
     */
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
    public ResultBean<GroupVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Group group = groupService.getById(id);
        //处理格式转换
        GroupVO groupVO = GroupConverter.INSTANT.entityToVO(group);
        return ResultBean.success(groupVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param groupQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<GroupVO>> list(@RequestBody GroupQuery groupQuery) {
        //处理格式转换
        Group group = GroupConverter.INSTANT.queryToEntity(groupQuery);
        //执行分页查询
        List<Group> listResult = groupService.list(new QueryWrapper<>(group));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, GroupVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery  分页对象
     * @param groupQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<GroupVO>> page(PageQuery pageQuery, GroupQuery groupQuery) {
        //处理分页条件
        Page<Group> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Group group = GroupConverter.INSTANT.queryToEntity(groupQuery);
        //执行分页查询
        Page<Group> pageResult = groupService.page(page, new QueryWrapper<>(group));
        PagerBean<GroupVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), GroupVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param groupQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/tree")
    @Operation(summary = "查询树结构")
    public ResultBean<List<GroupTree>> tree(@RequestBody GroupQuery groupQuery) {
        Group group = GroupConverter.INSTANT.queryToEntity(groupQuery);
        List<Group> listResult = groupService.getBaseMapper().selectList(Wrappers.<Group>lambdaQuery()
                .like(StringUtils.isNotEmpty(group.getGroupName()), Group::getGroupName, group.getGroupName())
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
     * 新增数据
     *
     * @param groupDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增用户组", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated GroupPostDTO groupDTO) {
        //处理格式转换
        Group group = GroupConverter.INSTANT.postDtoToEntity(groupDTO);
        //执行数据保存
        return ResultBean.success(groupService.save(group));
    }

    /**
     * 修改数据
     *
     * @param groupDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "修改用户组", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated GroupPutDTO groupDTO) {
        //处理格式转换
        Group group = GroupConverter.INSTANT.putDtoToEntity(groupDTO);
        //执行数据更新
        return ResultBean.success(groupService.updateById(group));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除用户组", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(groupService.removeByIds(ids));
    }

}

