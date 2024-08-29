package ai.remi.sys.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.log.annotation.LogRecord;
import ai.remi.comm.log.enums.BusinessType;
import ai.remi.comm.minio.service.MinIOService;
import ai.remi.comm.util.auth.AuthInfoUtils;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.file.FileTypeUtils;
import ai.remi.comm.util.file.FileUtils;
import ai.remi.comm.util.string.StringUtils;
import ai.remi.sys.domain.converter.FileConverter;
import ai.remi.sys.domain.entity.Bucket;
import ai.remi.sys.domain.entity.File;
import ai.remi.sys.domain.query.FileQuery;
import ai.remi.sys.domain.vo.FileVO;
import ai.remi.sys.server.service.BucketService;
import ai.remi.sys.server.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文件管理(File)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Tag(name = "文件管理")
@RequestMapping("file")
public class FileController {

    @Resource
    private BucketService bucketService;

    @Resource
    private FileService fileService;

    @Resource
    MinIOService minIOService;

    @Value("${minio.bucketName}")
    private String bucketName;

    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件")
    @LogRecord(content = "上传一个文件", businessType = BusinessType.UPLOAD)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<FileVO> upload(@RequestParam(name = "bucketCode", required = false) String bucketCode,
                                     @RequestParam(name = "directory", required = false) String directory,
                                     @RequestParam(name = "multipartFile") MultipartFile multipartFile) throws Exception {
        // 租户信息
        String companyTenantId = AuthInfoUtils.getCompanyTenantId();
        String deptTenantId = AuthInfoUtils.getDeptTenantId();
        // 原文件名
        String original = multipartFile.getOriginalFilename();
        // 文件类型
        String fileType = FileTypeUtils.getExtension(multipartFile);
        // 文件编码
        String fileCode = String.valueOf(System.currentTimeMillis());
        // 文件目录
        directory = StringUtils.isNotBlank(directory) ? directory : companyTenantId + "/" + deptTenantId + "/";
        // 文件名称
        String fileName = fileCode + "." + fileType;
        // 存储桶
        bucketCode = StringUtils.isNotBlank(bucketCode) ? bucketCode : bucketName;
        // 判断Bucket是否存在
        if (minIOService.bucketExists(bucketCode)) {
            // 文件上传
            minIOService.uploadFile(bucketCode, directory + fileName,
                    multipartFile.getInputStream());
        }
        // 文件入库
        Bucket bucket = bucketService.getOne(Wrappers.<Bucket>lambdaQuery().eq(Bucket::getBucketCode, bucketCode).last("limit 1"));
        File fileInfo = File.builder().bucketCode(bucketCode).bucketName(bucket.getBucketName()).deptTenantId(deptTenantId).companyTenantId(companyTenantId)
                .fileCode(fileCode).fileType(fileType).fileSize(FileUtils.formatSize(multipartFile.getSize())).filePath(directory).fileName(fileName).original(original).status(1).build();
        fileService.save(fileInfo);
        return ResultBean.success("上传成功", FileConverter.INSTANT.entityToVO(fileInfo));
    }

    @GetMapping("/download")
    @Operation(summary = "下载文件", description = "下载文件")
    @LogRecord(content = "下载一个文件", businessType = BusinessType.DOWNLOAD)
    public void downloadFile(@RequestParam(name = "bucketCode", required = false) String bucketCode,
                             @RequestParam(name = "directory", required = false) String directory,
                             @RequestParam(value = "fileName") String fileName, HttpServletResponse response) throws Exception {
        // 租户信息
        String companyTenantId = AuthInfoUtils.getCompanyTenantId();
        String deptTenantId = AuthInfoUtils.getDeptTenantId();
        // 文件目录
        directory = StringUtils.isNotBlank(directory) ? directory : companyTenantId + "/" + deptTenantId + "/";
        // 存储桶
        bucketCode = StringUtils.isNotBlank(bucketCode) ? bucketCode : bucketName;
        // 下载文件
        InputStream stream = minIOService.download(bucketCode, directory + fileName);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName.substring(fileName.lastIndexOf("/") + 1), "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(stream, response.getOutputStream());
    }

    @GetMapping("/preview")
    @Operation(summary = "预览文件", description = "预览文件")
    public ResultBean getPreviewFileUrl(@RequestParam(name = "bucketCode", required = false) String bucketCode,
                                        @RequestParam(name = "directory", required = false) String directory,
                                        @RequestParam(name = "fileName") String fileName) throws Exception {
        // 租户信息
        String companyTenantId = AuthInfoUtils.getCompanyTenantId();
        String deptTenantId = AuthInfoUtils.getDeptTenantId();
        // 文件目录
        directory = StringUtils.isNotBlank(directory) ? directory : companyTenantId + "/" + deptTenantId + "/";
        // 存储桶
        bucketCode = StringUtils.isNotBlank(bucketCode) ? bucketCode : bucketName;
        // 分享文件
        return ResultBean.success("预览成功！",minIOService.getPresignedObjectUrl(bucketCode, directory + fileName));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条", description = "查询单条")
    public ResultBean<FileVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        File file = fileService.getById(id);
        //处理格式转换
        FileVO fileVO = FileConverter.INSTANT.entityToVO(file);
        return ResultBean.success(fileVO);
    }


    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param fileQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public ResultBean<PagerBean<FileVO>> page(PageQuery pageQuery, FileQuery fileQuery) {
        //处理分页条件
        Page<File> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        File file = FileConverter.INSTANT.queryToEntity(fileQuery);
        //执行分页查询
        Page<File> pageResult = fileService.page(page, new QueryWrapper<>(file));
        PagerBean<FileVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), FileVO.class));
        return ResultBean.success(pageBean);
    }


    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "批量删除", description = "批量删除")
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) throws Exception {
        for (String id : ids) {
            File file = fileService.getById(id);
            minIOService.deleteObject(file.getBucketCode(), file.getCompanyTenantId() + "/" + file.getDeptTenantId() + "/" + file.getFileName());
            fileService.removeById(id);
        }
        return ResultBean.success();
    }

}

