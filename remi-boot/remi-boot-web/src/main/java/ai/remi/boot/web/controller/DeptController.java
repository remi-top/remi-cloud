package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.tree.TreeBuild;
import ai.remi.comm.exception.util.MessageUtils;
import ai.remi.comm.util.asserts.AssertUtils;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.id.SnowflakeUtils;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.boot.domain.converter.DeptConverter;
import ai.remi.boot.domain.dto.post.DeptPostDTO;
import ai.remi.boot.domain.dto.put.DeptPutDTO;
import ai.remi.boot.domain.entity.CompanyDept;
import ai.remi.boot.domain.entity.Dept;
import ai.remi.boot.domain.entity.DeptUser;
import ai.remi.boot.domain.entity.User;
import ai.remi.boot.domain.query.DeptQuery;
import ai.remi.boot.domain.tree.DeptTree;
import ai.remi.boot.domain.vo.DeptVO;
import ai.remi.boot.infra.mapper.DeptMapper;
import ai.remi.boot.server.service.CompanyDeptService;
import ai.remi.boot.server.service.DeptService;
import ai.remi.boot.server.service.DeptUserService;
import ai.remi.boot.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 部门表(Dept)控制层
 */
@Validated
@RestController
@Tag(name = "部门管理")
@RequestMapping("dept")
public class DeptController {

    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;

    @Resource
    private UserService userService;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private CompanyDeptService companyDeptService;

    @Resource
    private DeptUserService deptUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<DeptVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        DeptVO deptVO = deptMapper.selectOneById(id);
        //组装部门领导信息
        User user = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getId, deptVO.getDeptHeadId()));
        if (Objects.nonNull(user)) {
            deptVO.setDeptHeadName(user.getUserName());
            deptVO.setDeptHeadNameEn(user.getUserNameEn());
        }
        return ResultBean.success(deptVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param deptQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/tree")
    @Operation(summary = "查询树结构")
    public ResultBean<List<DeptTree>> tree(@RequestBody DeptQuery deptQuery) {
        List<DeptTree> deptTrees = deptMapper.selectDeptByCompanyId(deptQuery.getCompanyId(), deptQuery.getStatus());
        for (DeptTree deptTree : deptTrees) {
            //组装部门领导信息
            User user = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getId, deptTree.getDeptHeadId()).last("limit 1"));
            if (Objects.nonNull(user)) {
                deptTree.setDeptHeadName(user.getUserName());
                deptTree.setDeptHeadNameEn(user.getUserNameEn());
            }
        }
        //查询条件为空，默认查询根结点为0的一级部门
        if (CollectionUtils.isNotEmpty(deptTrees)) {
            String rootId = "0";
            // 创建树形结构并返回
            TreeBuild treeBuild = new TreeBuild(rootId, deptTrees);
            return ResultBean.success(treeBuild.buildTree());
        }
        return ResultBean.success(deptTrees);
    }

    /**
     * 新增数据
     *
     * @param deptDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "添加部门数据", businessType = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated DeptPostDTO deptDTO) {
        //处理格式转换
        Dept dept = DeptConverter.INSTANT.postDtoToEntity(deptDTO);
        dept.setId(SnowflakeUtils.getInstance().nextIdStr());
        //保存公司部门中间表
        CompanyDept one = companyDeptService.getOne(Wrappers.lambdaQuery(CompanyDept.class).eq(CompanyDept::getCompanyId, deptDTO.getCompanyId()).eq(CompanyDept::getDeptCode, deptDTO.getDeptCode()).last("limit 1"));
        //公司下已存在相同部门
        AssertUtils.isTrue(ObjectUtils.isEmpty(one), MessageUtils.getMessage("add.dept.error"));
        CompanyDept companyDept = CompanyDept.builder()
                .companyId(deptDTO.getCompanyId()).companyCode(deptDTO.getCompanyCode())
                .deptId(dept.getId()).deptCode(dept.getDeptCode()).build();
        companyDeptService.save(companyDept);
        //执行数据保存
        return ResultBean.success(deptService.save(dept));
    }

    /**
     * 修改数据
     *
     * @param deptDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "更新部门数据", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated DeptPutDTO deptDTO) {
        //处理格式转换
        Dept dept = DeptConverter.INSTANT.putDtoToEntity(deptDTO);
        //执行数据更新
        return ResultBean.success(deptService.updateById(dept));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除部门数据", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        for (String id : ids) {
            Dept dept = deptService.getOne(Wrappers.<Dept>lambdaQuery().eq(Dept::getParentId, id).last("limit 1"));
            DeptUser userDept = deptUserService.getOne(Wrappers.<DeptUser>lambdaQuery().eq(DeptUser::getDeptId, id).last("limit 1"));
            //当前部门下有子部门或员工未移除
            AssertUtils.isTrue(ObjectUtils.isEmpty(dept) || ObjectUtils.isEmpty(userDept), MessageUtils.getMessage("delete.dept.error"));
            //删除公司部门中间表
            LambdaQueryWrapper<CompanyDept> queryWrapper = Wrappers.lambdaQuery(CompanyDept.class).eq(CompanyDept::getDeptId, id);
            companyDeptService.remove(queryWrapper);
        }
        //删除部门信息
        return ResultBean.success(deptService.removeByIds(ids));
    }

}

