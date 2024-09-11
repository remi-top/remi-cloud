package ai.remi.comm.jdbc.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import ai.remi.comm.jdbc.handler.*;
import ai.remi.comm.jdbc.interceptor.FieldFillInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc MybatisPlusConfig 配置类
 */
@Slf4j
@Configuration
@MapperScan(value = {"ai.remi.*.infra.mapper"})
public class MybatisPlusConfig {

    @Resource
    private TenantConfig tenantConfig;

    @Resource
    private FieldFillConfig fieldFillConfig;

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户（部门）拦截器
        Boolean deptTenantEnable = tenantConfig.getDeptTenant().getEnable();
        if (deptTenantEnable){
            TenantLineInnerInterceptor tenantSqlParser = new TenantLineInnerInterceptor();
            tenantSqlParser.setTenantLineHandler(getDeptTenantInterceptor());
            interceptor.addInnerInterceptor(tenantSqlParser);
            log.info("----------------部门租户拦截器开启-----------------------");
        }

        // 多租户（公司）拦截器
        Boolean companyTenantEnable = tenantConfig.getCompanyTenant().getEnable();
        if (companyTenantEnable){
            TenantLineInnerInterceptor dealerSqlParser = new TenantLineInnerInterceptor();
            dealerSqlParser.setTenantLineHandler(getCompanyTenantInterceptor());
            interceptor.addInnerInterceptor(dealerSqlParser);
            log.info("----------------公司租户拦截器开启-----------------------");
        }

        //创建人填充
        Boolean createByEnable = fieldFillConfig.getCreatedByIntercept().getEnable();
        if (createByEnable){
            FieldFillInterceptor createByInterceptor = new FieldFillInterceptor();
            createByInterceptor.setFieldFillHandler(getCreatedByHandler());
            interceptor.addInnerInterceptor(createByInterceptor);
            log.info("----------------创建人填充器开启-----------------------");
        }

        //更新人填充
        Boolean updateByEnable = fieldFillConfig.getUpdateByIntercept().getEnable();
        if (updateByEnable){
            FieldFillInterceptor updateByInterceptor = new FieldFillInterceptor();
            updateByInterceptor.setFieldFillHandler(getUpdatedByHandler());
            interceptor.addInnerInterceptor(updateByInterceptor);
            log.info("----------------更新人填充器开启-----------------------");
        }

        //创建时间
        Boolean createAtEnable = fieldFillConfig.getCreateAtIntercept().getEnable();
        if (createAtEnable){
            FieldFillInterceptor createAtInterceptor = new FieldFillInterceptor();
            createAtInterceptor.setFieldFillHandler(getCreatedAtHandler());
            interceptor.addInnerInterceptor(createAtInterceptor);
            log.info("----------------创建时间填充器开启-----------------------");
        }
        //更新时间
        Boolean updateAtEnable = fieldFillConfig.getUpdateAtIntercept().getEnable();
        if (updateAtEnable){
            FieldFillInterceptor updateAtInterceptor = new FieldFillInterceptor();
            updateAtInterceptor.setFieldFillHandler(getUpdatedAtHandler());
            interceptor.addInnerInterceptor(updateAtInterceptor);
            log.info("----------------更新时间填充器开启-----------------------");
        }

        //自带分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));

        //自带乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return interceptor;
    }

    @Bean
    public UpdatedByHandler getUpdatedByHandler(){
        return new UpdatedByHandler(fieldFillConfig);
    }
    @Bean
    public UpdatedAtHandler getUpdatedAtHandler(){
        return new UpdatedAtHandler(fieldFillConfig);
    }


    @Bean
    public CreatedByHandler getCreatedByHandler(){
        return new CreatedByHandler(fieldFillConfig);
    }


    @Bean
    public CreatedAtHandler getCreatedAtHandler(){
        return new CreatedAtHandler(fieldFillConfig);
    }

    @Bean
    public DeptTenantHandler getDeptTenantInterceptor() {
        return new DeptTenantHandler(tenantConfig);
    }

    @Bean
    public CompanyTenantHandler getCompanyTenantInterceptor() {
        return new CompanyTenantHandler(tenantConfig);
    }


}
