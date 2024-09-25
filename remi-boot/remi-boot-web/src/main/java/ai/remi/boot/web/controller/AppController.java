package ai.remi.boot.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ai.remi.comm.core.result.PagerBean;
import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.domain.query.PageQuery;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.id.SnowflakeUtils;
import ai.remi.boot.domain.converter.AppConverter;
import ai.remi.boot.domain.converter.ConfigConverter;
import ai.remi.boot.domain.dto.post.AppPostDTO;
import ai.remi.boot.domain.dto.put.AppPutDTO;
import ai.remi.boot.domain.entity.App;
import ai.remi.boot.domain.entity.Config;
import ai.remi.boot.domain.query.AppQuery;
import ai.remi.boot.domain.vo.AppVO;
import ai.remi.boot.domain.vo.ConfigVO;
import ai.remi.boot.server.service.AppService;
import ai.remi.boot.server.service.ConfigService;
import ai.remi.boot.server.util.AppUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用表Application(App)控制层
 */
@Validated
@RestController
@Tag(name = "应用管理")
@RequestMapping("app")
public class AppController {
    /**
     * 服务对象
     */
    @Resource
    private AppService appService;
    @Resource
    private ConfigService configService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "查询单条")
    public ResultBean<AppVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        App app = appService.getById(id);
        //处理格式转换
        AppVO appVO = AppConverter.INSTANT.entityToVO(app);
        List<Config> list = configService.list(Wrappers.<Config>lambdaQuery().eq(Config::getBindId, id));
        appVO.setAppConfigs(BeanCopyUtils.coverList(list, ConfigVO.class));
        return ResultBean.success(appVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param appQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @Operation(summary = "查询所有")
    public ResultBean<List<AppVO>> list(@RequestBody AppQuery appQuery) {
        //处理格式转换
        App app = AppConverter.INSTANT.queryToEntity(appQuery);
        //执行分页查询
        List<App> listResult = appService.list(new QueryWrapper<>(app));
        return ResultBean.success(BeanCopyUtils.coverList(listResult, AppVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param appQuery  查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @Operation(summary = "分页查询")
    public ResultBean<PagerBean<AppVO>> page(PageQuery pageQuery, AppQuery appQuery) {
        //处理分页条件
        Page<App> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        App app = AppConverter.INSTANT.queryToEntity(appQuery);
        //执行分页查询
        Page<App> pageResult = appService.page(page, new QueryWrapper<>(app));
        PagerBean<AppVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyUtils.coverList(pageResult.getRecords(), AppVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param appDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增数据")
    //@LogRecord(content = "新增应用配置", businessType = BusinessType.INSERT)
    public ResultBean<Boolean> insert(@RequestBody @Validated AppPostDTO appDTO) {
        //处理格式转换
        App app = AppConverter.INSTANT.postDtoToEntity(appDTO);
        //应用ID
        String appId = SnowflakeUtils.getInstance().nextIdStr();
        app.setId(appId);
        //应用编码
        String appCode = AppUtils.getAppCode();
        app.setAppCode(appCode);
        //应用标识
        String appKey = AppUtils.getAppKey();
        app.setAppKey(appKey);
        //应用密钥
        String appSecret = AppUtils.getAppSecret(appKey);
        app.setAppSecret(appSecret);
        //保存应用数据
        appService.save(app);
        //更新应用全局配置
        Optional.ofNullable(appDTO.getAppConfigs())
                .map(List::stream)
                .ifPresent(stream -> stream.forEach(item -> {
                    Config config = ConfigConverter.INSTANT.postDtoToEntity(item);
                    configService.saveOrUpdate(config, Wrappers.<Config>lambdaUpdate().eq(Config::getConfCode, item.getConfCode())
                            .eq(Config::getBindId, appId)
                            .eq(Config::getBindCode, appCode));
                }));
        return ResultBean.success();
    }

    /**
     * 修改数据
     *
     * @param appDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    //@LogRecord(content = "修改应用配置", businessType = BusinessType.UPDATE)
    public ResultBean<Boolean> update(@RequestBody @Validated AppPutDTO appDTO) {
        //处理格式转换
        App app = AppConverter.INSTANT.putDtoToEntity(appDTO);
        //执行数据更新
        appService.updateById(app);
        //更新应用全局配置
        Optional.ofNullable(appDTO.getAppConfigs())
                .map(List::stream)
                .ifPresent(stream -> stream.forEach(item -> {
                    Config config = ConfigConverter.INSTANT.postDtoToEntity(item);
                    configService.saveOrUpdate(config, Wrappers.<Config>lambdaUpdate().eq(Config::getConfCode, item.getConfCode())
                            .eq(Config::getBindId, appDTO.getId())
                            .eq(Config::getBindCode, appDTO.getAppCode()));
                }));
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
    //@LogRecord(content = "删除应用数据", businessType = BusinessType.DELETE)
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(appService.removeByIds(ids));
    }

}

