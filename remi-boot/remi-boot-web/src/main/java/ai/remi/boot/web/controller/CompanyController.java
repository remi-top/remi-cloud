package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.tree.TreeBuild;
import ai.remi.comm.exception.util.MessageUtils;
import ai.remi.comm.util.asserts.AssertUtils;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.string.StringUtils;
import ai.remi.boot.domain.converter.CompanyConverter;
import ai.remi.boot.domain.dto.post.CompanyPostDTO;
import ai.remi.boot.domain.dto.put.CompanyPutDTO;
import ai.remi.boot.domain.entity.Company;
import ai.remi.boot.domain.entity.CompanyDept;
import ai.remi.boot.domain.query.CompanyQuery;
import ai.remi.boot.domain.tree.CompanyTree;
import ai.remi.boot.domain.vo.CompanyVO;
import ai.remi.boot.server.service.CompanyDeptService;
import ai.remi.boot.server.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 公司表(Company)控制层
 */
@Validated
@RestController
@Tag(name = "公司管理")
@RequestMapping("company")
public class CompanyController {

    /**
     * 服务对象
     */
    @Resource
    private CompanyService companyService;

    @Resource
    private CompanyDeptService companyDeptService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<CompanyVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Company company = companyService.getById(id);
        //处理格式转换
        CompanyVO companyVO = CompanyConverter.INSTANT.entityToVO(company);
        return ResultBean.success(companyVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param companyQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/tree")
    @Operation(summary = "查询树结构")
    public ResultBean<List<CompanyTree>> tree(@RequestBody CompanyQuery companyQuery) {
        Company company = CompanyConverter.INSTANT.queryToEntity(companyQuery);
        List<Company> listResult = companyService.getBaseMapper().selectList(Wrappers.<Company>lambdaQuery()
                .like(StringUtils.isNotEmpty(company.getCompanyName()), Company::getCompanyName, company.getCompanyName())
                .last("ORDER BY ISNULL( sort ) ASC,sort ASC"));
        // 原查询结果转换树形结构
        List<CompanyTree> companyTrees = BeanCopyUtils.coverList(listResult, CompanyTree.class);
        // TODO 后续从全局变量中取出租户信息，判断集团租户和公司租户是否是同一个值，如果是说明当前登陆的集团账户，此时公司根结点ID为0，否则公司根结点ID为当前用户登陆的公司ID
        if (CollectionUtils.isNotEmpty(companyTrees)) {
            String rootId = "0";
            //String companyTenantId = AuthInfoUtils.getCompanyTenantId();
            //String deptTenantId = AuthInfoUtils.getDeptTenantId();
            //if (companyTenantId.equals(deptTenantId)) {
            //    rootId = "0";
            //} else {
            //    rootId = deptTenantId;
            //}
            // 创建树形结构并返回
            TreeBuild treeBuild = new TreeBuild(rootId, companyTrees);
            List buildTree = treeBuild.buildTree();
            return ResultBean.success(buildTree);
        }
        return ResultBean.success(companyTrees);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param companyQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<CompanyVO>> list(@RequestBody CompanyQuery companyQuery) {
        //处理格式转换
        Company company = CompanyConverter.INSTANT.queryToEntity(companyQuery);
        //执行分页查询
        List<Company> listResult = companyService.list(new QueryWrapper<>(company));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, CompanyVO.class));
    }

    /**
     * 新增数据
     *
     * @param companyDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增公司数据", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated CompanyPostDTO companyDTO) {
        //处理格式转换
        Company company = CompanyConverter.INSTANT.postDtoToEntity(companyDTO);
        //执行数据保存
        return ResultBean.success(companyService.save(company));
    }

    /**
     * 修改数据
     *
     * @param companyDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "更新公司数据", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated CompanyPutDTO companyDTO) {
        //处理格式转换
        Company company = CompanyConverter.INSTANT.putDtoToEntity(companyDTO);
        //执行数据更新
        return ResultBean.success(companyService.updateById(company));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    //@LogRecord(content = "删除公司数据", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        for (String id : ids) {
            Company company = companyService.getOne(Wrappers.<Company>lambdaQuery().eq(Company::getParentId, id).last("limit 1"));
            CompanyDept companyDept = companyDeptService.getOne(Wrappers.<CompanyDept>lambdaQuery().eq(CompanyDept::getCompanyId, id).last("limit 1"));
            //当前公司下有子子公司或部门未移除
            AssertUtils.isTrue(Objects.nonNull(company) || Objects.nonNull(companyDept), MessageUtils.getMessage("delete.company.error"));
        }
        //删除公司信息
        return ResultBean.success(companyService.removeByIds(ids));
    }

}

