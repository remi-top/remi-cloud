package ai.remi.comm.util.security;

import cn.hutool.core.exceptions.UtilException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-26
 * @desc AesUtils 加/解密工具类
 * 使用密钥时请使用 initKey() 方法来生成随机密钥
 * initKey 方法内部使用 java.crypto.KeyGenerator 密钥生成器来生成特定于 AES 算法参数集的随机密钥
 * 使用密钥生成器生成的密钥能保证更强的随机性
 * 生成的二进制密钥建议使用 Hex 进行编码
 */
public class AesUtils {

    private AesUtils() {
    }

    /**
     * 密钥算法类型
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 密钥的默认位长度
     */
    public static final int DEFAULT_KEY_SIZE = 128;

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ECB_PKCS_5_PADDING = "AES/ECB/PKCS5Padding";
    public static final String ECB_NO_PADDING = "AES/ECB/NoPadding";

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] base64Decode(String base64Code) {
        return Base64.decodeBase64(base64Code);
    }

    public static byte[] aesEncryptToBytes(String content, String hexAesKey) throws Exception {
        return encrypt(content.getBytes(StandardCharsets.UTF_8), Hex.decodeHex(hexAesKey.toCharArray()));
    }

    public static String aesEncrypt(String content, String hexAesKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, hexAesKey));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String hexAesKey) throws Exception {
        byte[] decrypt = decrypt(encryptBytes, Hex.decodeHex(hexAesKey.toCharArray()));
        return new String(decrypt, StandardCharsets.UTF_8);
    }

    public static String aesDecrypt(String encryptStr, String hexAesKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), hexAesKey);
    }


    /**
     * 生成 Hex 格式默认长度的随机密钥
     * 32，解二进制后为 16 个字节
     *
     * @return String Hex 格式的随机密钥
     */
    public static String initHexKey() {
        return Hex.encodeHexString(initKey());
    }

    /**
     * 生成默认长度的随机密钥
     * 默认长度为 128
     *
     * @return byte[] 二进制密钥
     */
    public static byte[] initKey() {
        return initKey(DEFAULT_KEY_SIZE);
    }

    /**
     * 生成密钥
     * 128、192、256 可选
     *
     * @param keySize 密钥长度
     * @return byte[] 二进制密钥
     */
    public static byte[] initKey(int keySize) {
        // AES 要求密钥长度为 128 位、192 位或 256 位
        if (keySize != 128 && keySize != 192 && keySize != 256) {
            throw new RuntimeException("error keySize: " + keySize);
        }
        // 实例化
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException("no such algorithm exception: " + KEY_ALGORITHM, e);
        }
        keyGenerator.init(keySize);
        // 生成秘密密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥的二进制编码形式
        return secretKey.getEncoded();
    }

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     */
    private static Key toKey(byte[] key) {
        // 实例化 DES 密钥材料
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密的数据
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(data, key, ECB_PKCS_5_PADDING);
    }

    /**
     * 加密
     *
     * @param data            待加密数据
     * @param key             密钥
     * @param cipherAlgorithm 算法/工作模式/填充模式
     * @return byte[] 加密的数据
     */
    public static byte[] encrypt(byte[] data, byte[] key, final String cipherAlgorithm) {
        // 还原密钥
        Key k = toKey(key);
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            // 初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, k);

            // 发现使用 NoPadding 时，使用 ZeroPadding 填充
            if (ECB_NO_PADDING.equals(cipherAlgorithm)) {
                return cipher.doFinal(formatWithZeroPadding(data, cipher.getBlockSize()));
            }

            // 执行操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("AES encrypt error", e);
        }
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密的数据
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(data, key, ECB_PKCS_5_PADDING);
    }

    /**
     * 解密
     *
     * @param data            待解密数据
     * @param key             密钥
     * @param cipherAlgorithm 算法/工作模式/填充模式
     * @return byte[] 解密的数据
     */
    public static byte[] decrypt(byte[] data, byte[] key, final String cipherAlgorithm) {
        // 还原密钥
        Key k = toKey(key);
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            // 初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, k);

            // 发现使用 NoPadding 时，使用 ZeroPadding 填充
            if (ECB_NO_PADDING.equals(cipherAlgorithm)) {
                return removeZeroPadding(cipher.doFinal(data), cipher.getBlockSize());
            }

            // 执行操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("AES decrypt error", e);
        }
    }

    private static byte[] formatWithZeroPadding(byte[] data, final int blockSize) {
        final int length = data.length;
        final int remainLength = length % blockSize;

        if (remainLength > 0) {
            byte[] inputData = new byte[length + blockSize - remainLength];
            System.arraycopy(data, 0, inputData, 0, length);
            return inputData;
        }
        return data;
    }

    private static byte[] removeZeroPadding(byte[] data, final int blockSize) {
        final int length = data.length;
        final int remainLength = length % blockSize;
        if (remainLength == 0) {
            // 解码后的数据正好是块大小的整数倍，说明可能存在补 0 的情况，去掉末尾所有的 0
            int i = length - 1;
            while (i >= 0 && 0 == data[i]) {
                i--;
            }
            byte[] outputData = new byte[i + 1];
            System.arraycopy(data, 0, outputData, 0, outputData.length);
            return outputData;
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        //long current = Calendar.getInstance().getTimeInMillis();
        //long current = 1616031425922L;
        //System.out.println("\n当前时间戳为：" + current);


        // 使用密钥生成器 KeyGenerator 生成的 16 字节随机密钥的 hex 字符串，使用时解 hex 得到二进制密钥
        //byte[] bytes = AesUtils.initKey();
        //String aesKey = Hex.encodeHexString(bytes);
        String aesKey = "c39de8d1308e0ad7afbe1b4749c5d3a9";
        System.out.println("\n当前密钥为" + aesKey);


        //String content = "{\"isPhone\":\"0\",\"userid\":\"zhangsan\",\"passwd\":\"123456\",\"timestamp\":1610610307826}";
        String content = "tya_40hyJF";
        System.out.println("\nAES加密前：" + content);

        String encrypt = aesEncrypt(content, aesKey);
        System.out.println("\nAES加密后：" + encrypt);

        String decrypt = aesDecrypt(encrypt, aesKey);
        System.out.println("\nAES解密后：" + decrypt);

        //String bb = "{\"isPhone\":\"0\",\"userid\":\"zhangsan\",\"passwd\":\"123456\",\"timestamp\":1610610307826}";
        //String md5 = DigestUtils.md5DigestAsHex(bb.getBytes("UTF-8"));

        //System.out.println("\nmd5加密后:" + md5);
    }

}
