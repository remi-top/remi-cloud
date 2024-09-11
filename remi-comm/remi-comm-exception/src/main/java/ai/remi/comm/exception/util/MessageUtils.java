package ai.remi.comm.exception.util;

import ai.remi.comm.core.spring.SpringBeans;
import ai.remi.comm.util.auth.AuthInfoUtils;
import ai.remi.comm.util.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc MessageUtils
 */
@Component
public class MessageUtils {
    private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    /**
     * 获得多语言内容，默认根据当前用户的Local获取
     *
     * @param key 多语言key
     * @return 翻译后的值，如获取不到则返回key
     */
    public static String getMessage(String key) {
        return getMessage(key, new Object[]{});
    }


    /**
     * 获得多语言内容--带参数,默认根据当前用户的Local获取
     *
     * @param key    多语言key
     * @param params 参数
     * @return 翻译后的值，如获取不到则返回key
     */
    public static String getMessage(String key, Object[] params) {
        Locale locale = getLocale();
        if (params == null) {
            params = new Object[]{};
        }
        return getMessage(locale, key, params);

    }

    /**
     * 获取多语言内容--带默认值，默认根据当前用户的Local获取
     *
     * @param key        多语言key
     * @param defaultMsg 默认返回值
     * @return 翻译后的值，获取不到返回默认值
     */
    public static String getMessage(String key, String defaultMsg) {
        return getMessage(key, new Object[]{}, defaultMsg);
    }

    /**
     * 获得多语言内容--带参数--带默认值,默认根据当前用户的Local获取
     *
     * @param key        多语言key
     * @param defaultMsg 默认返回值
     * @param params     参数
     * @return 翻译后的值，获取不到返回默认值
     */
    public static String getMessage(String key, Object[] params, String defaultMsg) {
        Locale locale = getLocale();
        return getMessage(locale, key, params, defaultMsg);
    }

    /**
     * 不带参数
     *
     * @param locale 语言信息
     * @param key    多语言key
     * @return 翻译后的值
     */
    public static String getMessage(Locale locale, String key) {
        return getMessage(locale, key, new Object[]{});
    }


    /**
     * 获取多语言数据
     *
     * @param locale 语言信息
     * @param key    多语言key
     * @param params 参数
     * @return 翻译后的值
     */
    public static String getMessage(Locale locale, String key, Object[] params) {
        try {
            MessageSource messageSource = (MessageSource) SpringBeans.getBean("messageSource");
            return messageSource.getMessage(key, params, locale);
        } catch (Exception e) {
            logger.error("get locale msg error", e);
        }
        return key;
    }

    /**
     * 获取多语言数据
     *
     * @param key        多语言key
     * @param params     参数
     * @param defaultMsg 默认返回值
     * @param locale     语言信息
     * @return 翻译后的值
     */
    public static String getMessage(Locale locale, String key, Object[] params, String defaultMsg) {
        MessageSource messageSource = (MessageSource) SpringBeans.getBean("messageSource");
        return messageSource.getMessage(key, params, defaultMsg, locale);
    }

    /**
     * 获取当前语言
     *
     * @return
     */
    private static Locale getLocale() {
        String userLanguage = AuthInfoUtils.getUserLanguage();
        if (StringUtils.isBlank(userLanguage)) {
            return Locale.CHINA;
        }
        switch (userLanguage.toLowerCase()) {
            case "zh_cn":
            case "zh-CN":
            case "zh":
                return Locale.CHINA;
            default:
                return Locale.US;
        }
    }
}
