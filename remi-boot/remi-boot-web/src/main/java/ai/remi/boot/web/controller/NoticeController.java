package ai.remi.boot.web.controller;


import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.NoticeConverter;
import ai.remi.boot.domain.dto.post.NoticePostDTO;
import ai.remi.boot.domain.dto.put.NoticePutDTO;
import ai.remi.boot.domain.entity.Notice;
import ai.remi.boot.domain.query.NoticeQuery;
import ai.remi.boot.domain.vo.NoticeVO;
import ai.remi.boot.server.service.NoticeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
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
 * @desc 通知公告(Notice)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Tag(name = "通知公告")
@RequestMapping("notice")
public class NoticeController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<NoticeVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Notice notice = noticeService.getById(id);
        //处理格式转换
        NoticeVO noticeVO = NoticeConverter.INSTANT.entityToVO(notice);
        return ResultBean.success(noticeVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param noticeQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<NoticeVO>> list(@RequestBody NoticeQuery noticeQuery) {
        //处理格式转换
        Notice notice = NoticeConverter.INSTANT.queryToEntity(noticeQuery);
        //执行分页查询
        List<Notice> listResult = noticeService.list(new QueryWrapper<>(notice));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, NoticeVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery   分页对象
     * @param noticeQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<NoticeVO>> page(PageQuery pageQuery, NoticeQuery noticeQuery) {
        //处理分页条件
        Page<Notice> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Notice notice = NoticeConverter.INSTANT.queryToEntity(noticeQuery);
        //执行分页查询
        Page<Notice> pageResult = noticeService.page(page, new QueryWrapper<>(notice));
        PagerBean<NoticeVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), NoticeVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param noticeDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    public ResultBean<Boolean> insert(@RequestBody @Validated NoticePostDTO noticeDTO) {
        //处理格式转换
        Notice notice = NoticeConverter.INSTANT.postDtoToEntity(noticeDTO);
        //执行数据保存
        return ResultBean.success(noticeService.save(notice));
    }

    /**
     * 修改数据
     *
     * @param noticeDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated NoticePutDTO noticeDTO) {
        //处理格式转换
        Notice notice = NoticeConverter.INSTANT.putDtoToEntity(noticeDTO);
        //执行数据更新
        return ResultBean.success(noticeService.updateById(notice));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(noticeService.removeByIds(ids));
    }

}

