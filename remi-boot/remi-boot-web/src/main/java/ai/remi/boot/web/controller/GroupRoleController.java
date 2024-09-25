package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.exception.util.MessageUtils;
import ai.remi.comm.util.asserts.AssertUtils;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.boot.domain.converter.GroupRoleConverter;
import ai.remi.boot.domain.dto.post.GroupRolePostDTO;
import ai.remi.boot.domain.entity.Group;
import ai.remi.boot.domain.entity.GroupRole;
import ai.remi.boot.domain.query.GroupRoleQuery;
import ai.remi.boot.domain.vo.GroupRoleVO;
import ai.remi.boot.server.service.GroupRoleService;
import ai.remi.boot.server.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户组角色表(GroupRole)控制层
 */
@Validated
@RestController
@Tag(name = "用户组角色")
@RequestMapping("groupRole")
public class GroupRoleController {
    /**
     * 服务对象
     */
    @Resource
    private GroupRoleService groupRoleService;
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
    public ResultBean<GroupRoleVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        GroupRole groupRole = groupRoleService.getById(id);
        //处理格式转换
        GroupRoleVO groupRoleVO = GroupRoleConverter.INSTANT.entityToVO(groupRole);
        return ResultBean.success(groupRoleVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param groupRoleQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<GroupRoleVO>> list(@RequestBody GroupRoleQuery groupRoleQuery) {
        //处理格式转换
        GroupRole groupRole = GroupRoleConverter.INSTANT.queryToEntity(groupRoleQuery);
        //执行分页查询
        List<GroupRole> listResult = groupRoleService.list(new QueryWrapper<>(groupRole));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, GroupRoleVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery      分页对象
     * @param groupRoleQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<GroupRoleVO>> page(PageQuery pageQuery, GroupRoleQuery groupRoleQuery) {
        //处理分页条件
        Page<GroupRole> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        GroupRole groupRole = GroupRoleConverter.INSTANT.queryToEntity(groupRoleQuery);
        //执行分页查询
        Page<GroupRole> pageResult = groupRoleService.page(page, new QueryWrapper<>(groupRole));
        List<GroupRoleVO> groupRoleVOS = BeanCopyUtils.coverList(pageResult.getRecords(), GroupRoleVO.class);
        for (GroupRoleVO groupRoleVO : groupRoleVOS) {
            Group group = groupService.getById(groupRoleVO.getGroupId());
            groupRoleVO.setGroupName(group.getGroupName());
            groupRoleVO.setGroupNameEn(group.getGroupNameEn());
            groupRoleVO.setGroupRemark(group.getGroupRemark());
            groupRoleVO.setGroupRemarkEn(group.getGroupRemarkEn());
        }
        PagerBean<GroupRoleVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), groupRoleVOS);
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param groupRoleDTOs 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "给用户组角色批量分配用户组", businessType = BusinessType.GRANT)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated List<GroupRolePostDTO> groupRoleDTOs) {
        for (GroupRolePostDTO groupRoleDTO : groupRoleDTOs) {
            //处理格式转换
            GroupRole groupRole = GroupRoleConverter.INSTANT.postDtoToEntity(groupRoleDTO);
            GroupRole one = groupRoleService.getOne(Wrappers.<GroupRole>lambdaQuery().eq(GroupRole::getRoleId, groupRoleDTO.getRoleId()).eq(GroupRole::getGroupId, groupRoleDTO.getGroupId()).last("limit 1"));
            AssertUtils.isTrue(ObjectUtils.isEmpty(one), MessageUtils.getMessage("add.roleGroup.error", new Object[]{groupRoleDTO.getGroupCode()}));
            groupRoleService.save(groupRole);
        }
        //执行数据保存
        return ResultBean.success();
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "批量删除用户组角色已分配的用户组", businessType = BusinessType.GRANT)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(groupRoleService.removeByIds(ids));
    }
}

