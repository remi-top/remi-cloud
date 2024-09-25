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
import ai.remi.boot.domain.converter.GroupUserConverter;
import ai.remi.boot.domain.dto.post.GroupUserPostDTO;
import ai.remi.boot.domain.entity.User;
import ai.remi.boot.domain.entity.GroupUser;
import ai.remi.boot.domain.query.GroupUserQuery;
import ai.remi.boot.domain.vo.GroupUserVO;
import ai.remi.boot.server.service.GroupUserService;
import ai.remi.boot.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 组织用户(GroupUser)控制层
 */
@Validated
@RestController
@Tag(name = "组织用户")
@RequestMapping("groupUser")
public class GroupUserController {
    /**
     * 服务对象
     */
    @Resource
    private GroupUserService groupUserService;
    @Resource
    private UserService userService;

    /**
     * 分页查询所有数据
     *
     * @param pageQuery      分页对象
     * @param groupUserQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<GroupUserVO>> page(PageQuery pageQuery, GroupUserQuery groupUserQuery) {
        //处理分页条件
        Page<GroupUser> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        GroupUser groupUser = GroupUserConverter.INSTANT.queryToEntity(groupUserQuery);
        //执行分页查询
        Page<GroupUser> pageResult = groupUserService.page(page, new QueryWrapper<>(groupUser));
        List<GroupUserVO> groupUserVOS = BeanCopyUtils.coverList(pageResult.getRecords(), GroupUserVO.class);
        // 组装用户详细信息
        for (GroupUserVO groupUserVO : groupUserVOS) {
            User one = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getId, groupUserVO.getUserId()).last("limit 1"));
            if (Objects.nonNull(one)) {
                groupUserVO.setUserName(one.getUserName());
                groupUserVO.setUserNameEn(one.getUserNameEn());
                groupUserVO.setSex(one.getSex());
            }
        }
        PagerBean<GroupUserVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), groupUserVOS);
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param groupUserDTOs 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "给用户组批量分配用户", businessType = BusinessType.GRANT)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated List<GroupUserPostDTO> groupUserDTOs) {
        for (GroupUserPostDTO groupUserDTO : groupUserDTOs) {
            //处理格式转换
            GroupUser groupUser = GroupUserConverter.INSTANT.postDtoToEntity(groupUserDTO);
            GroupUser one = groupUserService.getOne(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getGroupId, groupUser.getGroupId()).eq(GroupUser::getUserId, groupUser.getUserId()).last("limit 1"));
            AssertUtils.isTrue(ObjectUtils.isEmpty(one), MessageUtils.getMessage("add.groupUser.error",new Object[]{groupUserDTO.getUserCode()}));
            groupUserService.save(groupUser);
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
    //@LogRecord(content = "批量删除用户组已分配的用户", businessType = BusinessType.GRANT)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(groupUserService.removeByIds(ids));
    }

}

