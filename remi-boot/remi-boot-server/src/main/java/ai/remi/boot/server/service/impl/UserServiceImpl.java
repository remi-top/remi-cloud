package ai.remi.boot.server.service.impl;

import ai.remi.boot.domain.converter.RoleConverter;
import ai.remi.boot.domain.entity.*;
import ai.remi.boot.domain.tree.MenuTree;
import ai.remi.boot.domain.vo.AppMenuVO;
import ai.remi.boot.domain.vo.ConfigVO;
import ai.remi.boot.domain.vo.RoleVO;
import ai.remi.boot.domain.vo.UserVO;
import ai.remi.boot.infra.mapper.MenuMapper;
import ai.remi.boot.infra.mapper.UserMapper;
import ai.remi.boot.server.service.*;
import ai.remi.comm.domain.tree.TreeBuild;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.object.ObjectUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户表(User)服务实现层
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private DeptUserService deptUserService;
    @Resource
    private CompanyDeptService companyDeptService;
    @Resource
    private CordRoleService cordRoleService;
    @Resource
    private UserPostService userPostService;
    @Resource
    private PostRoleService postRoleService;
    @Resource
    private GroupUserService groupUserService;
    @Resource
    private GroupRoleService groupRoleService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private ConfigService configService;

    @Override
    public UserVO getLoginById(String id) {
        UserVO userVO = userMapper.selectOneById(id);
        // 获取用户角色
        List<RoleVO> userRoles = getUserRoles(id);
        userVO.setUserRoles(userRoles);
        // 获取用户菜单
        List<AppMenuVO> userMenus = getUserMenus(userRoles);
        userVO.setUserMenus(userMenus);
        // 用户全局配置
        List<Config> list = configService.list(Wrappers.<Config>lambdaQuery().eq(Config::getConfType, 1).eq(Config::getBindId, id));
        userVO.setAppConfigs(BeanCopyUtils.coverList(list, ConfigVO.class));
        return userVO;
    }

    @Override
    public UserVO getUserById(String id) {
        UserVO userVO = userMapper.selectOneById(id);
        // 获取用户角色
        List<RoleVO> userRoles = getUserRoles(id);
        userVO.setUserRoles(userRoles);
        // 获取用户菜单
        //List<AppMenuVO> userMenus = getUserMenus(userRoles);
        //userVO.setUserMenus(userMenus);
        return userVO;
    }

    @Override
    public User login(String username, String password) {
        Wrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getLoginName, username).eq(User::getPassword, password).eq(User::getStatus, 1).last("limit 1");
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    private List<AppMenuVO> getUserMenus(List<RoleVO> userRoles) {
        List<AppMenuVO> list = new ArrayList<>();
        // 获取用户所有的菜单
        List<RoleMenu> roleMenus = roleMenuService.list(Wrappers.<RoleMenu>lambdaQuery().in(RoleMenu::getRoleId, userRoles.stream().map(a -> a.getId()).collect(Collectors.toList())));
        // 查询出所有菜单信息
        List<MenuTree> menuTrees = menuMapper.selectMenuInIds(roleMenus.stream().map(a -> a.getMenuId()).collect(Collectors.toList()), 1);
        Map<String, List<MenuTree>> collect = menuTrees.stream().collect(Collectors.groupingBy(menuTree -> menuTree.getAppId()));
        collect.forEach((key, value) -> {
            AppMenuVO appMenuVO = new AppMenuVO();
            appMenuVO.setAppId(key);
            if (CollectionUtils.isNotEmpty(value)) {
                appMenuVO.setAppCode(value.get(0).getAppCode());
                String rootId = "0";
                // 创建树形结构并返回
                TreeBuild treeBuild = new TreeBuild(rootId, value);
                appMenuVO.setMenuTree(treeBuild.buildTree());
            }
            list.add(appMenuVO);
        });
        return list;
    }

    private List<RoleVO> getUserRoles(String userId) {
        List<RoleVO> list = new ArrayList<>();
        // 1、基础角色
        List<UserRole> userRoles = userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        if (CollectionUtils.isNotEmpty(userRoles)) {
            for (UserRole userRole : userRoles) {
                Role one = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getId, userRole.getRoleId()).eq(Role::getRoleType, 1).last("limit 1"));
                list.add(RoleConverter.INSTANT.entityToVO(one));
            }
        }
        // 组织角色
        // 所在部门角色
        DeptUser deptUser = deptUserService.getOne(Wrappers.<DeptUser>lambdaQuery().eq(DeptUser::getUserId, userId).last("limit 1"));
        List<CordRole> deptRoles = cordRoleService.list(Wrappers.<CordRole>lambdaQuery().eq(CordRole::getCordId, deptUser.getDeptId()).eq(CordRole::getCordType, 2));
        if (CollectionUtils.isNotEmpty(deptRoles)) {
            for (CordRole cordRole : deptRoles) {
                Role one = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getId, cordRole.getRoleId()).eq(Role::getRoleType, 2).last("limit 1"));
                list.add(RoleConverter.INSTANT.entityToVO(one));
            }
        }
        // 所在公司角色
        CompanyDept companyDept = companyDeptService.getOne(Wrappers.<CompanyDept>lambdaQuery().eq(CompanyDept::getDeptId, deptUser.getDeptId()).last("limit 1"));
        List<CordRole> companyRoles = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(companyDept)) {
            companyRoles = cordRoleService.list(Wrappers.<CordRole>lambdaQuery().eq(CordRole::getCordId, companyDept.getCompanyId()).eq(CordRole::getCordType, 1));
        }
        if (CollectionUtils.isNotEmpty(companyRoles)) {
            for (CordRole cordRole : companyRoles) {
                Role one = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getId, cordRole.getRoleId()).eq(Role::getRoleType, 2).last("limit 1"));
                list.add(RoleConverter.INSTANT.entityToVO(one));
            }
        }
        // 业务角色
        UserPost userPost = userPostService.getOne(Wrappers.<UserPost>lambdaQuery().eq(UserPost::getUserId, userId).last("limit 1"));
        List<PostRole> postRoles = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(userPost)) {
            postRoles = postRoleService.list(Wrappers.<PostRole>lambdaQuery().eq(PostRole::getPostId, userPost.getPositionId()));
        }
        if (CollectionUtils.isNotEmpty(postRoles)) {
            for (PostRole postRole : postRoles) {
                Role one = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getId, postRole.getRoleId()).eq(Role::getRoleType, 3).last("limit 1"));
                list.add(RoleConverter.INSTANT.entityToVO(one));
            }
        }
        // 用户组角色
        List<GroupUser> groupUsers = groupUserService.list(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getUserId, userId));
        List<GroupRole> groupRoles = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(groupUsers)) {
            groupRoles = groupRoleService.list(Wrappers.<GroupRole>lambdaQuery().in(GroupRole::getGroupId, groupUsers.stream().map(a -> a.getGroupId()).collect(Collectors.toList())));
        }
        if (CollectionUtils.isNotEmpty(groupRoles)) {
            for (GroupRole groupRole : groupRoles) {
                Role one = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getId, groupRole.getRoleId()).eq(Role::getRoleType, 4).last("limit 1"));
                list.add(RoleConverter.INSTANT.entityToVO(one));
            }
        }
        // 角色集合去重返回
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().distinct().collect(Collectors.toList());
        }
        return list;
    }
}

