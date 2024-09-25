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
import ai.remi.boot.domain.converter.UserRoleConverter;
import ai.remi.boot.domain.dto.post.UserRolePostDTO;
import ai.remi.boot.domain.entity.User;
import ai.remi.boot.domain.entity.UserRole;
import ai.remi.boot.domain.query.UserRoleQuery;
import ai.remi.boot.domain.vo.UserRoleVO;
import ai.remi.boot.server.service.UserRoleService;
import ai.remi.boot.server.service.UserService;
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
 * @desc 用户角色表(UserRole)控制层
 */
@Validated
@RestController
@Tag(name = "用户角色")
@RequestMapping("userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<UserRoleVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        UserRole userRole = userRoleService.getById(id);
        //处理格式转换
        UserRoleVO userRoleVO = UserRoleConverter.INSTANT.entityToVO(userRole);
        return ResultBean.success(userRoleVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param userRoleQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<UserRoleVO>> list(@RequestBody UserRoleQuery userRoleQuery) {
        //处理格式转换
        UserRole userRole = UserRoleConverter.INSTANT.queryToEntity(userRoleQuery);
        //执行分页查询
        List<UserRole> listResult = userRoleService.list(new QueryWrapper<>(userRole));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, UserRoleVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery     分页对象
     * @param userRoleQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<UserRoleVO>> page(PageQuery pageQuery, UserRoleQuery userRoleQuery) {
        //处理分页条件
        Page<UserRole> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        UserRole userRole = UserRoleConverter.INSTANT.queryToEntity(userRoleQuery);
        //执行分页查询
        Page<UserRole> pageResult = userRoleService.page(page, new QueryWrapper<>(userRole));
        List<UserRoleVO> userRoleVOS = BeanCopyUtils.coverList(pageResult.getRecords(), UserRoleVO.class);
        for (UserRoleVO userRoleVO : userRoleVOS) {
            User user = userService.getById(userRoleVO.getUserId());
            userRoleVO.setUserName(user.getUserName());
            userRoleVO.setUserNameEn(user.getUserNameEn());
        }
        PagerBean<UserRoleVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), userRoleVOS);
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param userRoleDTOs 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "给基础角色批量分配用户", businessType = BusinessType.GRANT)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated List<UserRolePostDTO> userRoleDTOs) {
        for (UserRolePostDTO userRoleDTO : userRoleDTOs) {
            //处理格式转换
            UserRole userRole = UserRoleConverter.INSTANT.postDtoToEntity(userRoleDTO);
            UserRole one = userRoleService.getOne(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getRoleId, userRoleDTO.getRoleId()).eq(UserRole::getUserId, userRoleDTO.getUserId()).last("limit 1"));
            AssertUtils.isTrue(ObjectUtils.isEmpty(one), MessageUtils.getMessage("add.roleUser.error",new Object[]{userRoleDTO.getUserCode()}));
            userRoleService.save(userRole);
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
    //@LogRecord(content = "批量删除基础角色已分配的用户", businessType = BusinessType.GRANT)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(userRoleService.removeByIds(ids));
    }
}

