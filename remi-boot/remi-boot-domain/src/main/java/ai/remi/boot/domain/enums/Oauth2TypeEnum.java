package ai.remi.boot.domain.enums;

/**
 * Oauth2授权方式
 */
public enum Oauth2TypeEnum {
    /**
     * 授权码模式
     */
    AUTHORIZATION_CODE("code");

    private String value;

    Oauth2TypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
