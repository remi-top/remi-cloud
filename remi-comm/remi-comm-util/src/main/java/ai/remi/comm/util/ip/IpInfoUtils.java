package ai.remi.comm.util.ip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc IpUtils IP归属地工具类
 */
@Slf4j
public class IpInfoUtils {

    private static Searcher searcher;

    static {
        try (InputStream inputStream = IpInfoUtils.class.getClassLoader().getResourceAsStream("ip2region.xdb")) {
            if (inputStream == null) {
                throw new IOException("ip2region.xdb loading failed.");
            }
            byte[] buffer = new byte[8192];
            int bytesRead;
            try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                searcher = Searcher.newWithBuffer(output.toByteArray());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取 ip 对应的信息
     * @param ip
     * @return  格式： 中国|0|浙江|杭州|电信
     */
    public static IpInfo getInfo(String ip) {

        IpInfo ipInfo = new IpInfo(ip);

        if (!IpAddrUtils.validIp(ip)) {
            log.warn("========非法 IP 格式========");
            return ipInfo;
        }
        try {
            convert(searcher.search(ip),ipInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ipInfo;
    }

    private static IpInfo convert(String regionStr,IpInfo ipInfo) {
        if (regionStr == null || regionStr.length() == 0) {
            return null;
        }
        String[] regionSplit = regionStr.split("\\|");
        if (regionSplit.length != 5) {
            return null;
        }
        ipInfo.setCountry("0".equals(regionSplit[0]) ? null : regionSplit[0]);
        ipInfo.setRegion("0".equals(regionSplit[1]) ? null : regionSplit[1]);
        ipInfo.setProvince("0".equals(regionSplit[2]) ? null : regionSplit[2]);
        ipInfo.setCity("0".equals(regionSplit[3]) ? null : regionSplit[3]);
        ipInfo.setIsp("0".equals(regionSplit[4]) ? null : regionSplit[4]);
        return ipInfo;
    }

    @Setter
    @Getter
    @Accessors(chain = true)
    @ToString
    public static class IpInfo implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 国家
         */
        private String country = "未知";

        /**
         * 区域
         */
        private String region = "未知";

        /**
         * 省份
         */
        private String province = "未知";

        /**
         * 城市
         */
        private String city = "未知";

        /**
         * 运营商
         */
        private String isp = "未知";

        /**
         * IP地址
         */
        private String ip;

        public IpInfo(String ip) {
            this.ip = ip;
        }
    }

    public static void main(String[] args) {
        IpInfo info = getInfo("14.29.139.251");
        System.out.println(info.toString());
    }
}
