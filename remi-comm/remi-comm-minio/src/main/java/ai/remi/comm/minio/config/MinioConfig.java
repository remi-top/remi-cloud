package ai.remi.comm.minio.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author dianjiu【公众号 点九开源】
 * @email startdis@njydsz.com
 * @desc MinioConfig
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {

    @Resource
    private MinioProperties minioProperties;


    /**
     * 初始化 MinIO 客户端
     */
    @Bean
    public MinioClient minioClient() {
        try {
            return MinioClient.builder()
                    .endpoint(minioProperties.getEndpoint())
                    .credentials(minioProperties.getUsername(), minioProperties.getPassword())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Minio client", e);
        }
    }

}
