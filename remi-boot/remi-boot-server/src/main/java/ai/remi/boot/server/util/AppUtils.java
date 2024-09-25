package ai.remi.boot.server.util;

import ai.remi.comm.exception.util.MessageUtils;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.asserts.AssertUtils;
import ai.remi.comm.util.id.RandomUtils;
import ai.remi.comm.util.id.UUIDUtils;
import ai.remi.comm.util.security.AesUtils;
import ai.remi.boot.domain.enums.RedisKeyEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;

;

/**
 * @author DianJiu
 * @date 2021/12/8
 * @Description 随机产生唯一的app_key和app_secret
 */
@Component
public class AppUtils {

    @Resource
    private RedisService redisService;

    public static RedisService redisUtils;

    @PostConstruct
    public void init() {
        redisUtils = this.redisService;
    }

    private final static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 最大重试次数
     */
    public static final int MAX_RETRY_COUNT = 10;

    /**
     * 最大重试记录
     */
    public static int maxCount = 0;

    /**
     * 生成规则 年份后两位【2位】 + 1年内第几天【3位】 + 随机数【5位】
     * 理论上来说，一百年内不会重复
     * 注意：1.前五位保证每天生成的id不重复，后五位会在redis缓存 set集合中进行碰撞校验，
     * 2.请保证每天0时清除缓存，保证缓存中的短id数量较少提高性能，减少内存浪费
     */
    public static String getAppCode() {
        String year = String.valueOf(LocalDateTime.now().getYear()).substring(2);
        String day = String.valueOf(LocalDateTime.now().getDayOfYear());
        if (day.length() < 3) {
            StringBuilder sb = new StringBuilder();
            for (int i = day.length(); i < 3; i++) {
                sb.append("0");
            }
            day = sb.append(day).toString();
        }
        String code = year + day;
        int count = 0;
        boolean exists = true;
        while (exists) {
            if (count > maxCount) {
                maxCount = count;
            }
            String random = RandomUtils.generateNumberString(10 - code.length());
            exists = redisUtils.sSet(RedisKeyEnum.APP_CODE.getKey(code, random), code + random) == 0L;
            code = code + random;
            count++;
            AssertUtils.isFalse(count >= MAX_RETRY_COUNT, MessageUtils.getMessage("app.code.error"));
        }
        return code;
    }

    /**
     * @Description: <p>
     * 短8位UUID思想其实借鉴微博短域名的生成方式，但是其重复概率过高，而且每次生成4个，需要随即选取一个。
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，然后通过模62操作，结果作为索引取出字符，
     * 这样重复率大大降低。
     * 经测试，在生成一千万个数据也没有出现重复，完全满足大部分需求。
     * </p>
     * @author DianJiu
     * @date 2019/8/27 16:16
     */
    public static String getAppKey() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUIDUtils.simpleUuid();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString().toLowerCase();

    }

    /**
     * <p>
     * 通过appId和内置关键词生成APP Secret
     * </P>
     *
     * @author DianJiu
     * @date 2019/8/27 16:32
     */
    public static String getAppSecret() {
        return AesUtils.initHexKey();
    }

    public static Boolean validate(String appKey,String appSecret) {
        return appSecret.equals(getAppSecret(appKey));
    }

    /**
     * <p>
     * 通过appId和内置关键词生成APP Secret
     * </P>
     *
     * @author DianJiu
     * @date 2019/8/27 16:32
     */
    public static String getAppSecret(String appKey) {
        try {
            String[] array = new String[]{appKey};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public static void main(String[] args) {
        //for (int i = 0; i < 10000; i++) {
        //    getAppCode();
        //}
        //System.out.println("最大碰撞次数为=>" + AppUtils.maxCount);
        //String appCode = getAppCode();
        String appKey = getAppKey();
        String appSecret = getAppSecret(appKey);
        //System.out.println("appCode: " + appCode);
        System.out.println("appKey: " + appKey);
        System.out.println("appSecret: " + appSecret);
    }
}

