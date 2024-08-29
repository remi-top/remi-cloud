package ai.remi.sys.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.log.annotation.LogRecord;
import ai.remi.comm.log.enums.BusinessType;
import ai.remi.comm.minio.domian.BucketS3;
import ai.remi.comm.minio.service.MinIOService;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.sys.domain.converter.BucketConverter;
import ai.remi.sys.domain.dto.post.BucketPostDTO;
import ai.remi.sys.domain.dto.put.BucketPutDTO;
import ai.remi.sys.domain.entity.Bucket;
import ai.remi.sys.domain.query.BucketQuery;
import ai.remi.sys.domain.vo.BucketVO;
import ai.remi.sys.server.service.BucketService;
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
 * @desc 存储桶管理(Bucket)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Tag(name = "存储桶管理")
@RequestMapping("bucket")
public class BucketController {
    /**
     * 服务对象
     */
    @Resource
    private BucketService bucketService;

    @Resource
    MinIOService minIOService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<BucketVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) throws Exception {
        Bucket bucket = bucketService.getById(id);
        //处理格式转换
        BucketVO bucketVO = BucketConverter.INSTANT.entityToVO(bucket);
        return ResultBean.success(bucketVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param bucketQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<BucketVO>> list(@RequestBody BucketQuery bucketQuery) {
        //处理格式转换
        Bucket bucket = BucketConverter.INSTANT.queryToEntity(bucketQuery);
        //执行分页查询
        List<Bucket> listResult = bucketService.list(new QueryWrapper<>(bucket));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, BucketVO.class));
    }

    /**
     * 新增数据
     *
     * @param bucketDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    @LogRecord(content = "创建存储桶", businessType = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated BucketPostDTO bucketDTO) throws Exception {
        //处理格式转换
        Bucket bucket = BucketConverter.INSTANT.postDtoToEntity(bucketDTO);
        //执行数据保存
        minIOService.createBucket(BeanCopyUtils.copyPropertiesThird(bucket, BucketS3.class));
        bucketService.save(bucket);
        return ResultBean.success();
    }

    /**
     * 修改数据
     *
     * @param bucketDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    @LogRecord(content = "修改存储桶", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated BucketPutDTO bucketDTO) {
        //处理格式转换
        Bucket bucket = BucketConverter.INSTANT.putDtoToEntity(bucketDTO);
        //执行数据更新
        return ResultBean.success(bucketService.updateById(bucket));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据")
    @LogRecord(content = "删除存储桶", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) throws Exception {
        for (String id : ids) {
            Bucket bucket = bucketService.getById(id);
            minIOService.deleteBucket(bucket.getBucketCode());
            bucketService.removeById(id);
        }
        return ResultBean.success();
    }

}

