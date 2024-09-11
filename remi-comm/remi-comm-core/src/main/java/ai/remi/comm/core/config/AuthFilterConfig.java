package ai.remi.comm.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc AuthFilterConfig
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "filter.config")
public class AuthFilterConfig {

    /**
     * 公共忽略的url，网关、公共web的AuthFilter 都会忽略---- 配置在application-dev.yaml
     */
    private List<String> commonIgnoreUrl= new ArrayList<>();

    /**
     * 网关忽略的ur----配置在ebg-shop-gateway-dev.yaml
     */
    private List<String> gatewayIgnoreUrl=new ArrayList<>();

    /**
     *各个服务自定义忽略的url---配置在自己的服务
     */
    private List<String> customIgnoreUrl= new ArrayList<>();

    /**
     * 是否验证权限----配置在idaas服务
     */
    private Boolean verifyPermission=true;
    /**
     * 只验证token不验证 权限的
     */
    private List<String> onlyVerifyToken=new ArrayList<>();

}
