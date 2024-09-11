package ai.remi.comm.util.security;

import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc Rsa2Utils
 */
public class Rsa2Utils {
    public static final String CHARSET = "UTF-8";
    //密钥算法
    public static final String ALGORITHM_RSA = "RSA";
    //RSA 签名算法
    public static final String ALGORITHM_RSA_SIGN = "SHA256WithRSA";
    public static final int ALGORITHM_RSA_PRIVATE_KEY_LENGTH = 2048;
    //用于封装随机产生的公钥与私钥
    private static Map<String, String> keyPairMap = new HashMap<String, String>();

    private Rsa2Utils() {
    }

    public static void main(String[] args) {
        //生成公钥私钥
        initRSAKey(2048);
        //私钥
        String privateKey = keyPairMap.get("privateKey");
        System.out.println("生成私钥："+privateKey);
        //String privateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDH4u7Shk55VvrOt7pEcyV4eLETNVgec1hGwtRc/YicYLNS8SSQlozESidoo7rh03QYHrEKswhltPfo63fOydkFWMAbX1M9VbJQin9OR2Xxu/OGBAUrXPArdfzlLpMtG+xsG2iapVBXOHx77E1DX0AehDw/jDGAawlOyqSavdWlVDDLQP2312FLvR9TjfBly9P3oQbbum9JrSwgkJM+rjwvElUC4Rs48w6l0RI+hJIe4RIoTyXefBQI4jNUrcVsJZpJEtp4khlmDJKx632fumg9zi4tctS/3MZTGoJaA1zei/UuzXD33nHsL+6ZAwCSayidLeXjKCn5CCAQO6PGKg2pAgMBAAECggEAA/+fJWj4YucBh2cXe80N39YSPL8hbyKBjpDRLLcJp48rpyoZwCay1C5jkW1NDL1tIhpjEkK4lUhEeU1UjTdudvPqi+GhSaXQNlo+57L38BkBaxnjfJWUXFo03+TJnupjFVqLAiTycXw4yUXV2dYq8zFT/zVvvBDoE8Jr/ohzoKYAb4ziahDPg7D15oaX8S6dAa/qBoSapLSO+rN6eELEqAaH8r4QVCtVpVzDLXcIpF9kgaJmAkJfrb9Cmi6dA7MBbwCjEK/mtyGJTZaw7ZTJR6+CumPmGZCjWppNawX/8ISYbZeAmX9mGSEp6iI17ciYZt79YfQGDOSvRgjBA67vrQKBgQDu4X5AVaxQwLKLFfuhoim9ZmB4/MAQntvCD5Oe7RQM/eepSX9LIuDcoj7tDjqCvPemEosLoR05WHxe8eLNFwcUzkiQFdhB2lNn02rBn8rCHTwZvf1WHwhkNTi0/szRgZexzRIqrUJrNlAiezsbcYxeoOsLqnKzQExNM4mTvPkcDwKBgQDWNgyPHTGXLpXjCPJ8HF92xveUOwXb+H8SVs5AVHwg+kJNvtjqAwASPnaBs9xMCEaXChF/2O04AUB29comjPZ3By0VmWiH3ngOucsIGih/9RDs0JTWaNaMDYvhA9huXKeIRpo8hyTLcLJ8Y0XVHgi8a68tnP+qfA5kANC17kDixwKBgQC9ayLQYt7rfZFWBo1soojUfZyb81ddFoqNfAqDZ9yRXLhYnUYJz1NcwG6DTNgKmUsKbgPBqfThZw/fvZApLkxYLCaP0jKULUobtyIc/NcrS0E/7E44/wyJYUZhBvZkXo1p2IHCQ3a6SuwKG4tH/Kbg1lrq+jJPfvnB8c7yzlL98wKBgBHKbWKZdxvqR5KSlKkQJjGUNs291S++xLb+NKUNHz4R6t3QIu3Uj/Xz8B23lW1PJqW2tVtb5d/H4tW+diqbfIDIPMtVcWQPawM2FRG4MnLmJKC2dhYUoPUCzwJCMoB/+34K9xZd1wV6d6hzCkK3+tvDScpNBsLfCMbvdmYVwpfTAoGBAMv12bQYwCK/EmezCl484AvNJ9/oIjqz1QyK141HXb5QXIXN4DL//TcngslDvqpqvoVXM4TRoc8Ne6aQeRaNgIeOuYeyixWi48x6csN5FC41lnUdEkPYIxqZCrkxglS8J/oLKRiu385gQPxd2EqVE21snajRoY3CiKXQkchxyycB";
        //公钥
        String publicKey = keyPairMap.get("publicKey");
        System.out.println("生成公钥："+publicKey);
        //String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx+Lu0oZOeVb6zre6RHMleHixEzVYHnNYRsLUXP2InGCzUvEkkJaMxEonaKO64dN0GB6xCrMIZbT36Ot3zsnZBVjAG19TPVWyUIp/Tkdl8bvzhgQFK1zwK3X85S6TLRvsbBtomqVQVzh8e+xNQ19AHoQ8P4wxgGsJTsqkmr3VpVQwy0D9t9dhS70fU43wZcvT96EG27pvSa0sIJCTPq48LxJVAuEbOPMOpdESPoSSHuESKE8l3nwUCOIzVK3FbCWaSRLaeJIZZgySset9n7poPc4uLXLUv9zGUxqCWgNc3ov1Ls1w995x7C/umQMAkmsonS3l4ygp+QggEDujxioNqQIDAQAB";
        //私钥加密解密
        String encryptByPrivateKey = buildRSAEncryptByPrivateKey("ABCabc123测试", privateKey);
        System.out.println("私钥加密："+encryptByPrivateKey);
        String decryptByPublicKey = buildRSADecryptByPublicKey(encryptByPrivateKey, publicKey);
        System.out.println("公钥解密："+decryptByPublicKey);
        //公钥加密解密
        //String encryptByPublicKey = buildRSAEncryptByPublicKey("ABCabc123测试", publicKey);
        //System.out.println("公钥加密："+encryptByPublicKey);
        //String decryptByPrivateKey = buildRSADecryptByPrivateKey(encryptByPublicKey, privateKey);
        //System.out.println("私钥解密："+decryptByPrivateKey);
        //使用私钥生成数字签名
        String privateValue = buildRSASignByPrivateKey("ABCabc123测试", privateKey);
        System.out.println("私钥加签："+privateValue);
        //使用公钥校验数字签名
        boolean publicValue = buildRSAverifyByPublicKey("ABCabc123测试", publicKey, privateValue);
        System.out.println("公钥验签："+publicValue);

    }
    /**
     * 初始化RSA算法密钥对
     *
     * @param keysize RSA1024已经不安全了,建议2048
     * @return 经过Base64编码后的公私钥Map, 键名分别为publicKey和privateKey
     */
    public static void initRSAKey(int keysize) {
        if (keysize != ALGORITHM_RSA_PRIVATE_KEY_LENGTH) {
            throw new IllegalArgumentException("RSA1024已经不安全了,请使用" + ALGORITHM_RSA_PRIVATE_KEY_LENGTH + "初始化RSA密钥对");
        }
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + ALGORITHM_RSA + "]");
        }
        //初始化KeyPairGenerator对象,不要被initialize()源码表面上欺骗,其实这里声明的size是生效的
        kpg.initialize(ALGORITHM_RSA_PRIVATE_KEY_LENGTH);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);
    }

    /**
     * RSA算法公钥加密数据
     *
     * @param data 待加密的明文字符串
     * @param key  RSA公钥字符串
     * @return RSA公钥加密后的经过Base64编码的密文字符串
     */
    public static String buildRSAEncryptByPublicKey(String data, String key) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }
    /**
     * RSA算法公钥解密数据
     *
     * @param data 待解密的经过Base64编码的密文字符串
     * @param key  RSA公钥字符串
     * @return RSA公钥解密后的明文字符串
     */
    public static String buildRSADecryptByPublicKey(String data, String key) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data)), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }
    /**
     * RSA算法私钥加密数据
     *
     * @param data 待加密的明文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥加密后的经过Base64编码的密文字符串
     */
    public static String buildRSAEncryptByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }
    /**
     * RSA算法私钥解密数据
     * @param data 待解密的经过Base64编码的密文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥解密后的明文字符串
     */
    public static String buildRSADecryptByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data)), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }
    /**
     * RSA算法使用私钥对数据生成数字签名
     *
     * @param data 待签名的明文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥签名后的经过Base64编码的字符串
     */
    public static String buildRSASignByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initSign(privateKey);
            signature.update(data.getBytes(CHARSET));
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException("签名字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法使用公钥校验数字签名
     *
     * @param data 参与签名的明文字符串
     * @param key  RSA公钥字符串
     * @param sign RSA签名得到的经过Base64编码的字符串
     * @return true--验签通过,false--验签未通过
     */
    public static boolean buildRSAverifyByPublicKey(String data, String key, String sign) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initVerify(publicKey);
            signature.update(data.getBytes(CHARSET));
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            throw new RuntimeException("验签字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法分段加解密数据
     *
     * @param cipher 初始化了加解密工作模式后的javax.crypto.Cipher对象
     * @param opmode 加解密模式,值为javax.crypto.Cipher.ENCRYPT_MODE/DECRYPT_MODE
     * @return 加密或解密后得到的数据的字节数组
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = ALGORITHM_RSA_PRIVATE_KEY_LENGTH / 8;
        } else {
            maxBlock = ALGORITHM_RSA_PRIVATE_KEY_LENGTH / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

}
