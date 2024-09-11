package ai.remi.comm.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dianjiu【公众号 点九开源】
 * @email startdis@njydsz.com
 * @desc Minio配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * 端点
     */
    private String endpoint;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 桶名称
     */
    private String bucketName;
}
