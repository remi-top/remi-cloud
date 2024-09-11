package ai.remi.comm.util.id;

import ai.remi.comm.util.ip.MacAddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SequenceUtils {

    private static final Logger log = LoggerFactory.getLogger(SequenceUtils.class);


    public static String OLD_DATETIME = "";
    /**
     * 静态属性
     */
    public static int STATIC_SEQUENCE = 0;

    /**
     * 默认节点
     */
    public static String DEFAULT_NODE_NUMBER = "01";

    public static String STATIC_NODE_NUMBER = "--";

    /**
     * 生成20位的流水号
     *
     * @return 流水号
     */
    public static synchronized String next() {
        String currentDateTime = getCurrentSystemDateTime();

        if (null == currentDateTime) {
            log.error("获取系统日期失败");
            return null;
        }

        StringBuffer sequenceNumber = new StringBuffer();

        sequenceNumber.append(currentDateTime.substring(0, 8));
        sequenceNumber.append(getNodeNumber());
        sequenceNumber.append(currentDateTime.substring(8));
        sequenceNumber.append(nextSequence());
        return sequenceNumber.toString();
    }

    public static final String initNodeNumber(String nodeNumbers) {
        if (STATIC_NODE_NUMBER.equals("--")) {
            if (null != nodeNumbers && !nodeNumbers.equals("")) {

                String ipAddressConfigValue = nodeNumbers;
                log.info("ARE config.node.number : " + ipAddressConfigValue);
                if (ipAddressConfigValue.indexOf(",") > -1) {

                    String hostIpAddress = MacAddressUtils.getAllHostMacAddress();//获得本机IP

                    log.info("hostIpAddress : " + hostIpAddress);

                    String[] ipAddressValues = ipAddressConfigValue.split(",");
                    for (String ipvalue : ipAddressValues) {
                        String[] ipNode = ipvalue.split("=");
                        if (ipNode != null && ipNode.length > 0 && hostIpAddress.indexOf(ipNode[0]) > -1) {
                            STATIC_NODE_NUMBER = ipNode[1];
                        }
                    }

                    if (STATIC_NODE_NUMBER.equals("--")) {
                        log.error("GET MAC BIND NODE ERROR . ");
                        STATIC_NODE_NUMBER = DEFAULT_NODE_NUMBER;
                    }

                } else {
                    STATIC_NODE_NUMBER = nodeNumbers;
                }
                log.info("STATIC_NODE_SEQUENCE_NUMBER : " + STATIC_NODE_NUMBER);
                if (STATIC_NODE_NUMBER.length() != 2) {
                    log.error("系统节点号必须2位");
                }
            } else {
                STATIC_NODE_NUMBER = DEFAULT_NODE_NUMBER;
            }
        }
        return STATIC_NODE_NUMBER;
    }

    public static final String getNodeNumber() {
        return STATIC_NODE_NUMBER;
    }

    /**
     * 同一时刻只有一个访问
     *
     * @return
     */
    private static final synchronized String nextSequence() {
        STATIC_SEQUENCE = (STATIC_SEQUENCE + 1) % 10000;
        return String.format("%04d", STATIC_SEQUENCE);
    }

    /**
     * 获取系统当前日期，格式为yyyyMMddHHmmSS
     *
     * @return 当前系统日期
     */
    private static synchronized String getCurrentSystemDateTime() {
        String currentdatetime = null;
        synchronized (OLD_DATETIME) {
            currentdatetime = (new java.text.SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
            /**
             * 判断是否是新的时间，如果是新时间则STATIC_SEQUENCE从0开始计数
             */
            if (!currentdatetime.equals(OLD_DATETIME)) {
                STATIC_SEQUENCE = 0;
                OLD_DATETIME = currentdatetime;
            }
        }
        return currentdatetime;
    }
}
