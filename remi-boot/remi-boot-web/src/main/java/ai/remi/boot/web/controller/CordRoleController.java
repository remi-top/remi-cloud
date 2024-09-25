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
import ai.remi.boot.domain.converter.CordRoleConverter;
import ai.remi.boot.domain.dto.post.CordRolePostDTO;
import ai.remi.boot.domain.entity.Company;
import ai.remi.boot.domain.entity.CordRole;
import ai.remi.boot.domain.entity.Dept;
import ai.remi.boot.domain.query.CordRoleQuery;
import ai.remi.boot.domain.vo.CordRoleVO;
import ai.remi.boot.server.service.CompanyService;
import ai.remi.boot.server.service.CordRoleService;
import ai.remi.boot.server.service.DeptService;
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
 * @desc 组织角色表(CordRole)控制层
 */
@Validated
@RestController
@Tag(name = "组织角色")
@RequestMapping("cordRole")
public class CordRoleController {
    /**
     * 服务对象
     */
    @Resource
    private CordRoleService cordRoleService;
    @Resource
    private CompanyService companyService;
    @Resource
    private DeptService deptService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<CordRoleVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        CordRole cordRole = cordRoleService.getById(id);
        //处理格式转换
        CordRoleVO cordRoleVO = CordRoleConverter.INSTANT.entityToVO(cordRole);
        return ResultBean.success(cordRoleVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param cordRoleQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<CordRoleVO>> list(@RequestBody CordRoleQuery cordRoleQuery) {
        //处理格式转换
        CordRole cordRole = CordRoleConverter.INSTANT.queryToEntity(cordRoleQuery);
        //执行分页查询
        List<CordRole> listResult = cordRoleService.list(new QueryWrapper<>(cordRole));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, CordRoleVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery     分页对象
     * @param cordRoleQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<CordRoleVO>> page(PageQuery pageQuery, CordRoleQuery cordRoleQuery) {
        //处理分页条件
        Page<CordRole> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        CordRole cordRole = CordRoleConverter.INSTANT.queryToEntity(cordRoleQuery);
        //执行分页查询
        Page<CordRole> pageResult = cordRoleService.page(page, new QueryWrapper<>(cordRole));
        List<CordRoleVO> cordRoleVOS = BeanCopyUtils.coverList(pageResult.getRecords(), CordRoleVO.class);
        for (CordRoleVO cordRoleVO : cordRoleVOS) {
            if (cordRoleVO.getCordType() == 1) {
                Company company = companyService.getById(cordRoleVO.getCordId());
                cordRoleVO.setCompanyName(company.getCompanyName());
                cordRoleVO.setCompanyNameEn(company.getCompanyNameEn());
            }
            if (cordRoleVO.getCordType() == 2) {
                Dept dept = deptService.getById(cordRoleVO.getCordId());
                cordRoleVO.setDeptName(dept.getDeptName());
                cordRoleVO.setDeptNameEn(dept.getDeptNameEn());
            }
        }
        PagerBean<CordRoleVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), cordRoleVOS);
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param cordRoleDTOs 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "给组织角色批量分配组织（公司/部门）", businessType = BusinessType.GRANT)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated List<CordRolePostDTO> cordRoleDTOs) {
        for (CordRolePostDTO cordRoleDTO : cordRoleDTOs) {
            //处理格式转换
            CordRole cordRole = CordRoleConverter.INSTANT.postDtoToEntity(cordRoleDTO);
            CordRole one = cordRoleService.getOne(Wrappers.<CordRole>lambdaQuery().eq(CordRole::getRoleId, cordRoleDTO.getRoleId()).eq(CordRole::getCordId, cordRoleDTO.getCordId()).last("limit 1"));
            AssertUtils.isTrue(ObjectUtils.isEmpty(one), MessageUtils.getMessage("add.roleCord.error",new Object[]{cordRoleDTO.getCordCode()}));
            cordRoleService.save(cordRole);
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
    //@LogRecord(content = "批量删除组织角色已分配的组织（公司/部门）", businessType = BusinessType.GRANT)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(cordRoleService.removeByIds(ids));
    }
}

