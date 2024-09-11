package ai.remi.comm.exception.util;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc ExceptionUtils
 */
public class ExceptionUtils {


    /**
     * 获取exception的详细错误信息。
     */
    public static String getTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }

}
