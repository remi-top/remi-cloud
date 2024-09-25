package ai.remi.boot.server.util;

import ai.remi.comm.util.security.Base64Utils;
import ai.remi.comm.util.string.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class AuthUtils {
    public static String getAuthCode(String appKey, String state) {
        String encode = Base64Utils.encode(appKey + state);
        if (StringUtils.isNotBlank(encode)) {
            return DigestUtils.md5DigestAsHex(encode.getBytes(StandardCharsets.UTF_8));
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(getAuthCode("AB123CE","123456"));
    }
}
