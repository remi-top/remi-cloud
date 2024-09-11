package ai.remi.comm.util.asserts;

import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.comm.util.string.StringUtils;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类
 */
public class AssertUtils {
    /**
     * 如果不是true，则抛异常
     * @param expression
     * @param msg
     */
    @SneakyThrows
    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果不是false，则抛异常
     * @param expression
     * @param msg
     */
    @SneakyThrows
    public static void isFalse(boolean expression, String msg) {
        if (expression) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果不是空或者空字符串，则抛异常
     * @param str
     * @param msg
     */
    @SneakyThrows
    public static void isEmpty(String str, String msg) {
        if (StringUtils.hasText(str)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果是空或者空字符串，则抛异常
     * @param str
     * @param msg
     */
    @SneakyThrows
    public static void isNotEmpty(String str, String msg) {
        if (!StringUtils.hasText(str)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果数组为不空或者长度大于等于1，则抛异常
     * @param str
     * @param msg
     */
    @SneakyThrows
    public static void isEmpty(Object[] str, String msg) {
        if (!ObjectUtils.isEmpty(str)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果数组为空或者长度小于1，则抛异常
     * @param str
     * @param msg
     */
    @SneakyThrows
    public static void isNotEmpty(Object[] str, String msg) {
        if (ObjectUtils.isEmpty(str)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果集合不为空或者长度大于等于1，则抛异常
     * @param collection
     * @param msg
     */
    @SneakyThrows
    public static void isEmpty(Collection collection, String msg) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果集合为空或者长度小于1，则抛异常
     * @param collection
     * @param msg
     */
    @SneakyThrows
    public static void isNotEmpty(Collection collection, String msg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果集合不为空或者长度大于等于1，则抛异常
     * @param map
     * @param msg
     */
    @SneakyThrows
    public static void isEmpty(Map map, String msg) {
        if (CollectionUtils.isNotEmpty(map)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果集合为空或者长度小于1，则抛异常
     * @param map
     * @param msg
     */
    @SneakyThrows
    public static void isNotEmpty(Map map, String msg) {
        if (CollectionUtils.isEmpty(map)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果对象不为空，则抛异常
     * @param object
     * @param msg
     */
    @SneakyThrows
    public static void isNull(Object object, String msg){
        if (object != null) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果对象空，则抛异常
     * @param object
     * @param msg
     */
    @SneakyThrows
    public static void isNotNull(Object object, String msg){
        if (object == null) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果对象不为空，则抛异常
     * @param objects
     * @param msg
     */
    @SneakyThrows
    public static void isNull(Object[] objects, String msg) {
        if (!ObjectUtils.isEmpty(objects)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 如果对象空，则抛异常
     * @param objects
     * @param msg
     */
    @SneakyThrows
    public static void isNotNull(Object[] objects, String msg) {
        if (ObjectUtils.isEmpty(objects)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 比较对象
     * @param obj1
     * @param obj2
     * @param msg
     */
    @SneakyThrows
    public static void equals(Object obj1, Object obj2, String msg) {
        if (!equals(obj1, obj2)) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 比较对象的相等
     * @param cs1
     * @param cs2
     * @return
     */
    public static boolean equals(final Object cs1, final Object cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (null == cs1 && null != cs2) {
            return false;
        }
        if (null != cs1 && null == cs2) {
            return false;
        }
        if (cs1 == null || null == cs2) {
            return true;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        return cs1.equals(cs2);
    }
}
