package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.auth.AuthInfoUtils;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.boot.domain.converter.ConfigConverter;
import ai.remi.boot.domain.dto.post.ConfigPostDTO;
import ai.remi.boot.domain.entity.Config;
import ai.remi.boot.domain.query.ConfigQuery;
import ai.remi.boot.domain.vo.ConfigVO;
import ai.remi.boot.server.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 全局配置(Config)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Tag(name = "全局配置")
@RequestMapping("config")
public class ConfigController {
    /**
     * 服务对象
     */
    @Resource
    private ConfigService configService;

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param configQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<ConfigVO>> list(@RequestBody ConfigQuery configQuery) {
        //处理格式转换
        Config config = ConfigConverter.INSTANT.queryToEntity(configQuery);
        //执行分页查询
        List<Config> listResult = configService.list(new QueryWrapper<>(config));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, ConfigVO.class));
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @return 对象列表
     */
    @PostMapping(value = "/getAppConfigs")
    @Operation(summary = "查询应用全局配置")
    public ResultBean<List<ConfigVO>> getAppConfigs(@RequestParam("appId") String appId, @RequestParam("confType") Integer confType) {
        //处理格式转换
        Config config = Config.builder().bindId(appId).confType(confType).build();
        //执行分页查询
        List<Config> listResult = configService.list(new QueryWrapper<>(config));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, ConfigVO.class));
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @return 对象列表
     */
    @PostMapping(value = "/getUserConfigs")
    @Operation(summary = "查询用户全局配置")
    public ResultBean<List<ConfigVO>> getUserConfigs() {
        String uniqueId = AuthInfoUtils.getUniqueId();
        //处理格式转换
        Config config = Config.builder().bindId(uniqueId).confType(1).build();
        //执行分页查询
        List<Config> listResult = configService.list(new QueryWrapper<>(config));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, ConfigVO.class));
    }

    /**
     * 保存配置
     *
     * @param configPosts 实体对象
     * @return 新增结果
     */
    @PostMapping("/operate")
    @Operation(summary = "保存配置")
    //@LogRecord(content = "保存全局配置", businessType = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public ResultBean<Boolean> insert(@RequestBody @Validated List<ConfigPostDTO> configPosts) {
        for (ConfigPostDTO configPost : configPosts) {
            //处理格式转换
            Config config = ConfigConverter.INSTANT.postDtoToEntity(configPost);
            configService.saveOrUpdate(config, Wrappers.<Config>lambdaUpdate().eq(Config::getConfCode, configPost.getConfCode())
                    .eq(StringUtils.isNotBlank(configPost.getBindId()), Config::getBindId, configPost.getBindId())
                    .eq(StringUtils.isNotBlank(configPost.getBindCode()), Config::getBindCode, configPost.getBindCode()));
        }
        return ResultBean.success("保存成功");
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery   分页对象
     * @param configQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<ConfigVO>> page(PageQuery pageQuery, ConfigQuery configQuery) {
        //处理分页条件
        Page<Config> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Config config = ConfigConverter.INSTANT.queryToEntity(configQuery);
        //执行分页查询
        Page<Config> pageResult = configService.page(page, new QueryWrapper<>(config));
        PagerBean<ConfigVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), ConfigVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "批量删除")
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(configService.removeByIds(ids));
    }

}

