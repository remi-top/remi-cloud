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
import ai.remi.boot.domain.converter.PostRoleConverter;
import ai.remi.boot.domain.dto.post.PostRolePostDTO;
import ai.remi.boot.domain.entity.PostRole;
import ai.remi.boot.domain.entity.Position;
import ai.remi.boot.domain.query.PostRoleQuery;
import ai.remi.boot.domain.vo.PostRoleVO;
import ai.remi.boot.server.service.PositionService;
import ai.remi.boot.server.service.PostRoleService;
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
 * @desc 职位角色表(PostRole)控制层
 */
@Validated
@RestController
@Tag(name = "业务角色")
@RequestMapping("postRole")
public class PostRoleController {
    /**
     * 服务对象
     */
    @Resource
    private PostRoleService postRoleService;
    @Resource
    private PositionService positionService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<PostRoleVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        PostRole postRole = postRoleService.getById(id);
        //处理格式转换
        PostRoleVO postRoleVO = PostRoleConverter.INSTANT.entityToVO(postRole);
        return ResultBean.success(postRoleVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param postRoleQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<PostRoleVO>> list(@RequestBody PostRoleQuery postRoleQuery) {
        //处理格式转换
        PostRole postRole = PostRoleConverter.INSTANT.queryToEntity(postRoleQuery);
        //执行分页查询
        List<PostRole> listResult = postRoleService.list(new QueryWrapper<>(postRole));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, PostRoleVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery     分页对象
     * @param postRoleQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<PostRoleVO>> page(PageQuery pageQuery, PostRoleQuery postRoleQuery) {
        //处理分页条件
        Page<PostRole> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        PostRole postRole = PostRoleConverter.INSTANT.queryToEntity(postRoleQuery);
        //执行分页查询
        Page<PostRole> pageResult = postRoleService.page(page, new QueryWrapper<>(postRole));
        List<PostRoleVO> postRoleVOS = BeanCopyUtils.coverList(pageResult.getRecords(), PostRoleVO.class);
        for (PostRoleVO postRoleVO : postRoleVOS) {
            Position position = positionService.getById(postRoleVO.getPostId());
            postRoleVO.setPositionName(position.getPositionName());
            postRoleVO.setPositionNameEn(position.getPositionNameEn());
        }
        PagerBean<PostRoleVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), postRoleVOS);
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param postRoleDTOs 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "给业务角色批量分配职位", businessType = BusinessType.GRANT)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated List<PostRolePostDTO> postRoleDTOs) {
        for (PostRolePostDTO postRoleDTO : postRoleDTOs) {
            //处理格式转换
            PostRole postRole = PostRoleConverter.INSTANT.postDtoToEntity(postRoleDTO);
            PostRole one = postRoleService.getOne(Wrappers.<PostRole>lambdaQuery().eq(PostRole::getRoleId, postRoleDTO.getRoleId()).eq(PostRole::getPostId, postRoleDTO.getPostId()).last("limit 1"));
            AssertUtils.isTrue(ObjectUtils.isEmpty(one), MessageUtils.getMessage("add.rolePost.error", new Object[]{postRoleDTO.getPostCode()}));
            postRoleService.save(postRole);
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
    //@LogRecord(content = "批量删除业务角色已分配的职位", businessType = BusinessType.GRANT)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(postRoleService.removeByIds(ids));
    }
}

