package ai.remi.comm.util.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import lombok.SneakyThrows;
import org.apache.commons.lang3.Validate;

/**
 * 不可逆加密工具类
 */
public class DigestUtils {

    private static SecureRandom random = new SecureRandom();

    /**
     * 生成随机的Byte[]作为salt密钥.
     *
     * @param numBytes byte数组的大小
     */
    public static byte[] genSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     *
     * @param input      需要散列的字符串
     * @param algorithm  散列算法（"SHA-1"、"MD5"）
     * @param salt
     * @param iterations 迭代次数
     * @return
     */
    @SneakyThrows
    public static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        if (salt != null) {
            digest.update(salt);
        }

        byte[] result = digest.digest(input);

        for (int i = 1; i < iterations; i++) {
            digest.reset();
            result = digest.digest(result);
        }
        return result;
    }

    /**
     * 对文件进行sha1散列.
     *
     * @param input     需要散列的流
     * @param algorithm 散列算法（"SHA-1"、"MD5"）
     */
    @SneakyThrows
    public static byte[] digest(InputStream input, String algorithm) throws IOException {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return messageDigest.digest();
    }

}