package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.LogRecordConverter;
import ai.remi.boot.domain.dto.post.LogRecordPostDTO;
import ai.remi.boot.domain.dto.put.LogRecordPutDTO;
import ai.remi.boot.domain.entity.LogRecord;
import ai.remi.boot.domain.query.LogRecordQuery;
import ai.remi.boot.domain.vo.LogRecordVO;
import ai.remi.boot.infra.mapper.UserMapper;
import ai.remi.boot.server.service.LogRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 日志记录表(LogRecord)控制层
 */
@Validated
@RestController
@Tag(name = "日志记录")
@RequestMapping("logRecord")
public class LogRecordController {
    /**
     * 服务对象
     */
    @Resource
    private LogRecordService logRecordService;
    @Resource
    private UserMapper userMapper;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get")
    @Operation(summary = "查询单条")
    public ResultBean<LogRecordVO> get(@RequestParam String id) {
        LogRecord logRecord = logRecordService.getById(id);
        //处理格式转换
        LogRecordVO logRecordVO = LogRecordConverter.INSTANT.entityToVO(logRecord);
        return ResultBean.success(logRecordVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param logRecordQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<LogRecordVO>> list(@RequestBody LogRecordQuery logRecordQuery) {
        //处理格式转换
        LogRecord logRecord = LogRecordConverter.INSTANT.queryToEntity(logRecordQuery);
        //执行分页查询
        List<LogRecord> listResult = logRecordService.list(new QueryWrapper<>(logRecord));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, LogRecordVO.class));
    }

    /**
     * 分页查询登录日志
     *
     * @param pageQuery      分页对象
     * @param logRecordQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/pageLogin")
    @Operation(summary = "分页查询登录日志")
    public ResultBean<PagerBean<LogRecordVO>> pageLogin(PageQuery pageQuery, LogRecordQuery logRecordQuery) {
        //处理分页条件
        Page<LogRecord> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        LogRecord logRecord = LogRecordConverter.INSTANT.queryToEntity(logRecordQuery);
        //执行分页查询
        LambdaQueryWrapper<LogRecord> queryWrapper = Wrappers.<LogRecord>lambdaQuery().in(LogRecord::getBusinessType, List.of(9, 10, 11)).orderByDesc(LogRecord::getCreatedAt);
        Page<LogRecord> pageResult = logRecordService.page(page, queryWrapper);
        PagerBean<LogRecordVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), LogRecordVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 分页查询操作日志
     *
     * @param pageQuery      分页对象
     * @param logRecordQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/pageOperate")
    @Operation(summary = "分页查询操作日志")
    public ResultBean<PagerBean<LogRecordVO>> pageOperate(PageQuery pageQuery, LogRecordQuery logRecordQuery) {
        //处理分页条件
        Page<LogRecord> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        LogRecord logRecord = LogRecordConverter.INSTANT.queryToEntity(logRecordQuery);
        //执行分页查询
        LambdaQueryWrapper<LogRecord> queryWrapper = Wrappers.<LogRecord>lambdaQuery().in(LogRecord::getBusinessType, List.of(1, 2, 3, 4, 5, 6, 7, 8, 12, 13, 14, 15, 99)).orderByDesc(LogRecord::getCreatedAt);
        Page<LogRecord> pageResult = logRecordService.page(page, queryWrapper);
        PagerBean<LogRecordVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), LogRecordVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 分页查询异常日志
     *
     * @param pageQuery      分页对象
     * @param logRecordQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/pageException")
    @Operation(summary = "分页查询异常日志")
    public ResultBean<PagerBean<LogRecordVO>> pageException(PageQuery pageQuery, LogRecordQuery logRecordQuery) {
        //处理分页条件
        Page<LogRecord> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        LogRecord logRecord = LogRecordConverter.INSTANT.queryToEntity(logRecordQuery);
        //执行分页查询
        LambdaQueryWrapper<LogRecord> queryWrapper = Wrappers.<LogRecord>lambdaQuery().isNotNull(LogRecord::getErrorInfo).orderByDesc(LogRecord::getCreatedAt);
        Page<LogRecord> pageResult = logRecordService.page(page, queryWrapper);
        PagerBean<LogRecordVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), LogRecordVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery      分页对象
     * @param logRecordQuery 查询实体
     * @return 分页对象
     */
    //@PostMapping(value = "/page")
    //@Operation(summary = "分页查询")
    //public ResultBean<PagerBean<LogRecordVO>> page(PageQuery pageQuery, LogRecordQuery logRecordQuery) {
    //    //处理分页条件
    //    Page<LogRecord> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
    //    //处理格式转换
    //    LogRecord logRecord = LogRecordConverter.INSTANT.queryToEntity(logRecordQuery);
    //    //执行分页查询
    //    Page<LogRecord> pageResult = logRecordService.page(page, new QueryWrapper<>(logRecord));
    //    PagerBean<LogRecordVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
    //            pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), LogRecordVO.class));
    //    return ResultBean.success(pageBean);
    //}

    /**
     * 新增数据
     *
     * @param logRecordDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    public ResultBean<Boolean> insert(@RequestBody @Validated LogRecordPostDTO logRecordDTO) {
        // 处理格式转换
        LogRecord logRecord = LogRecordConverter.INSTANT.postDtoToEntity(logRecordDTO);
        //执行数据保存
        return ResultBean.success(logRecordService.save(logRecord));
    }

    /**
     * 修改数据
     *
     * @param logRecordDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated LogRecordPutDTO logRecordDTO) {
        //处理格式转换
        LogRecord logRecord = LogRecordConverter.INSTANT.putDtoToEntity(logRecordDTO);
        //执行数据更新
        return ResultBean.success(logRecordService.updateById(logRecord));
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
        return ResultBean.success(logRecordService.removeByIds(ids));
    }

}

