package ai.remi.boot.web.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.exception.util.MessageUtils;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.asserts.AssertUtils;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.id.SnowflakeUtils;
import ai.remi.boot.domain.converter.UserConverter;
import ai.remi.boot.domain.dto.post.UserPostDTO;
import ai.remi.boot.domain.dto.put.UserPutDTO;
import ai.remi.boot.domain.entity.DeptUser;
import ai.remi.boot.domain.entity.User;
import ai.remi.boot.domain.entity.UserPost;
import ai.remi.boot.domain.enums.RedisKeyEnum;
import ai.remi.boot.domain.query.UserQuery;
import ai.remi.boot.domain.vo.UserVO;
import ai.remi.boot.infra.mapper.UserMapper;
import ai.remi.boot.server.service.DeptUserService;
import ai.remi.boot.server.service.UserPostService;
import ai.remi.boot.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户表(User)控制层
 */
@Validated
@RestController
@Tag(name = "用户管理")
@RequestMapping("user")
public class UserController {

    /**
     * 服务对象
     */
    @Resource
    private RedisService redisService;
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DeptUserService deptUserService;
    @Resource
    private UserPostService userPostService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过userId获取用户信息")
    public ResultBean<UserVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        UserVO userVO = userService.getUserById(id);
        return ResultBean.success(userVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param token 主键
     * @return 单条数据
     */
    @GetMapping("token/{token}")
    @Operation(summary = "通过Token获取用户信息")
    public ResultBean<UserVO> getUserByToken(@PathVariable @Validated @NotBlank(message = "Token不能为空") String token) throws Exception {
        Map<Object, Object> hmget = redisService.hmget(RedisKeyEnum.USER_INFO.getKey(token));
        AssertUtils.isTrue(CollectionUtils.isNotEmpty(hmget), MessageUtils.getMessage("check.userinfo.token.error"));
        return ResultBean.success(BeanUtil.toBean(hmget, UserVO.class));
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param userQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<UserVO>> list(@RequestBody UserQuery userQuery) {
        //执行分页查询
        List<UserVO> listResult = userMapper.selectListByUser(userQuery);
        return ResultBean.success(listResult);
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param userQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<UserVO>> page(PageQuery pageQuery, UserQuery userQuery) {
        //查询总条数
        Long total = userMapper.selectCountByUser(userQuery);
        //执行分页查询
        List<UserVO> userVOS = userMapper.selectPageByUser(pageQuery.getPageNum() - 1, pageQuery.getPageSize(), userQuery);
        PagerBean<UserVO> pageBean = new PagerBean<>(total, Long.valueOf(pageQuery.getPageNum()), Long.valueOf(pageQuery.getPageSize()), userVOS);
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param userDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "录入用户信息", businessType = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated UserPostDTO userDTO) {
        //处理格式转换
        User user = UserConverter.INSTANT.postDtoToEntity(userDTO);
        user.setId(SnowflakeUtils.getInstance().nextIdStr());
        //保存部门用户中间表
        DeptUser deptUser = DeptUser.builder()
                .deptId(userDTO.getDeptId())
                .deptCode(userDTO.getDeptCode())
                .userId(user.getId()).userCode(user.getUserCode()).build();
        deptUserService.save(deptUser);
        // 保存用户职位职级（即岗位）
        UserPost userPost = UserPost.builder()
                .positionId(userDTO.getPositionId()).positionCode(userDTO.getPositionCode())
                .rankId(userDTO.getRankId()).rankCode(userDTO.getRankCode())
                .userId(user.getId()).userCode(user.getUserCode()).build();
        userPostService.save(userPost);
        //执行用户信息保存
        return ResultBean.success(userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param userDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "更新用户信息", businessType = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> update(@RequestBody @Validated UserPutDTO userDTO) {
        //处理格式转换
        User user = UserConverter.INSTANT.putDtoToEntity(userDTO);
        //保存公司部门中间表
        DeptUser deptUser = DeptUser.builder()
                .deptId(userDTO.getDeptId())
                .deptCode(userDTO.getDeptCode())
                .userId(user.getId()).userCode(user.getUserCode()).build();
        deptUserService.saveOrUpdate(deptUser, Wrappers.<DeptUser>lambdaUpdate().eq(DeptUser::getUserId, userDTO.getId()));
        // 保存用户职位职级（即岗位）
        UserPost userPost = UserPost.builder()
                .positionId(userDTO.getPositionId()).positionCode(userDTO.getPositionCode())
                .rankId(userDTO.getRankId()).rankCode(userDTO.getRankCode())
                .userId(user.getId()).userCode(user.getUserCode()).build();
        userPostService.saveOrUpdate(userPost, Wrappers.<UserPost>lambdaUpdate().eq(UserPost::getUserId, userDTO.getId()));
        //执行数据更新
        return ResultBean.success(userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除用户信息", businessType = BusinessType.DELETE)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        for (String id : ids) {
            // 删除部门用户中间表
            LambdaQueryWrapper<DeptUser> deptUserQuery = Wrappers.lambdaQuery(DeptUser.class).eq(DeptUser::getUserId, id);
            deptUserService.remove(deptUserQuery);
            // 删除用户岗位中间表
            LambdaQueryWrapper<UserPost> userPostQuery = Wrappers.lambdaQuery(UserPost.class).eq(UserPost::getUserId, id);
            userPostService.remove(userPostQuery);
        }
        return ResultBean.success(userService.removeByIds(ids));
    }

}

