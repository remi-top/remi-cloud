package ai.remi.comm.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 错误码设计描述
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ServiceTypeEnum {

    WEB_SERVICE("webService", "/web-api/**", "管理端服务"),
    APP_SERVICE("appService", "/app-api/**", "移动端服务");

    private String code;

    /**
     * 服务对应的路径前缀
     */
    private String pathPrefix;

    private String desc;


    public static ServiceTypeEnum codeOf(String code) {
        return Arrays.stream(values())
            .filter((each) -> each.getCode().equals(code)).findFirst()
            .orElseThrow(()->new RuntimeException("找不到服务"));
    }

    public static ServiceTypeEnum pathPrefixOf(String pathPrefix){
        return Arrays.stream(values())
            .filter((each) -> each.getPathPrefix().equals(pathPrefix)).findFirst()
            .orElseThrow(()->new RuntimeException("找不到服务"));
    }

}
