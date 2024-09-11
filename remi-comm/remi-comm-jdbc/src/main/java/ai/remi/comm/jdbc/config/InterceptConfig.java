package ai.remi.comm.jdbc.config;

import ai.remi.comm.jdbc.enums.InterceptTableStrategy;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 拦截器配置类
 */
@Data
public class InterceptConfig {
    /**
     * 拦截表策略,默认拦截不包含这些表
     */
    private InterceptTableStrategy interceptTableStrategy = InterceptTableStrategy.EXCLUDE;

    /**
     * 是否打开拦截配置
     */
    private Boolean enable = false;

    /**
     * 不拦截或拦截的表
     */
    private Set<String> tables = new HashSet<>();

    /**
     * 处理的字段
     */
    private String column = "";
}
