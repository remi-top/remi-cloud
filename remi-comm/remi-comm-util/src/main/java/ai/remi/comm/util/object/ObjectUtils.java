package ai.remi.comm.util.object;

import ai.remi.comm.util.string.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc ObjectUtils 对象工具箱
 */
public class ObjectUtils {

    /**
     * 判断字符串是否为true
     *
     * @param str
     * @return
     */
    public static boolean isStrTrue(String str) {
        return StringUtils.isNotEmpty(str) && "true".equals(str);
    }

    /**
     * 判断对象是否不为空
     *
     * @param object
     * @return: boolean
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return: boolean
     */
    public static boolean isEmpty(Object object) {
        boolean aNull = isNull(object);
        if (!aNull) {
            //普通的类对象
            Field[] fields = object.getClass().getDeclaredFields();
            //先假设全部属性都是空的，所以只要出现一个属性不为空的就不需要在循环判断
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (!"serialVersionUID".equals(field.getName()) && !ObjectUtils.isNull(field.get(object))) {
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 判断对象是否不为空
     *
     * @param object
     * @return: boolean
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return: boolean
     */
    public static boolean isNull(Object object) {
        //判断对象是否为null
        if (null == object) {
            return true;
        }
        //判断对象是否为容器对象类型
        if (object instanceof Optional) {
            return !((Optional) object).isPresent();
        }
        //判断对象是否为字符集合类型
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        //判断对象是否为数组类型
        if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        }
        //判断对象是否为集合类型
        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        }
        //判断对象是否为Map
        return object instanceof Map ? ((Map) object).isEmpty() : false;
    }

    /**
     * 判断obejct对象中除了names里面的字段，其他字段都为null（已知对象类型）
     *
     * @param object
     * @param names
     * @return
     */
    public static boolean isEmpty(Object object, String... names) {
        Field[] fields = object.getClass().getDeclaredFields();
        //用于判断所有属性是否为空,如果参数为空则不查询
        boolean flag = true;
        for (Field field : fields) {
            //不检查 直接取值
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                List<String> nameList = new ArrayList<>();
                if (null != names && names.length != 0) {
                    nameList = Arrays.asList(names);
                }
                if (!nameList.contains(fieldName) && !Objects.isNull(field.get(object))) {
                    //不为空
                    flag = false;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 判断object对象中除了names里面的字段，其他字段都为null或者是""（已知对象类型）
     *
     * @param object
     * @param names
     * @return
     */
    public static boolean isBlank(Object object, String... names) {
        Field[] fields = object.getClass().getDeclaredFields();
        //用于判断所有属性是否为空,如果参数为空则不查询
        boolean flag = true;
        for (Field field : fields) {
            //不检查 直接取值
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                List<String> nameList = new ArrayList<>();
                if (null != names && names.length != 0) {
                    nameList = Arrays.asList(names);
                }
                Object value = field.get(object);
                if (!nameList.contains(fieldName) && !isEmpty(value)) {
                    //不为空
                    flag = false;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 利用反射 将Java实体转化为 map
     *
     * @param object object
     * @return map
     * @version 1.0
     */
    public static Map<String, Object> objectToMap(Object object) {
        Map<String, Object> reMap = new HashMap<>();
        if (object == null) {
            return null;
        }
        List<Field> fields = new ArrayList<>();
        List<Field> childFields;
        List<String> fieldsName = new ArrayList<>();
        Class tempClass = object.getClass();
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (tempClass != null) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            //得到父类,然后赋给自己
            tempClass = tempClass.getSuperclass();
        }
        childFields = Arrays.asList(object.getClass().getDeclaredFields());
        for (Field field : childFields) {
            fieldsName.add(field.getName());
        }
        try {
            for (Field field : fields) {
                try {
                    if (fieldsName.contains(field.getName())) {
                        Field f = object.getClass().getDeclaredField(field.getName());
                        f.setAccessible(true);
                        Object o = f.get(object);
                        reMap.put(field.getName(), o);
                    } else {
                        Field f = object.getClass().getSuperclass().getDeclaredField(field.getName());
                        f.setAccessible(true);
                        Object o = f.get(object);
                        reMap.put(field.getName(), o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

    /**
     * 利用反射将map集合封装成bean对象
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T mapToObject(Map<Object, Object> map, Class<?> clazz) throws Exception {
        Object obj = clazz.newInstance();
        if (map != null && !map.isEmpty() && map.size() > 0) {
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                String propertyName = String.valueOf(entry.getKey());    // 属性名
                Object value = entry.getValue();        // 属性值
                String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                Field field = getClassField(clazz, propertyName);    //获取和map的key匹配的属性名称
                if (field == null) {
                    continue;
                }
                Class<?> fieldTypeClass = field.getType();
                value = convertValType(value, fieldTypeClass);
                try {
                    clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return (T) obj;
    }

    /**
     * 根据给定对象类匹配对象中的特定字段
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        Class<?> superClass = clazz.getSuperclass();    //如果该类还有父类，将父类对象中的字段也取出
        if (superClass != null) {                        //递归获取
            return getClassField(superClass, fieldName);
        }
        return null;
    }

    /**
     * 将map的value值转为实体类中字段类型匹配的方法
     *
     * @param value
     * @param fieldTypeClass
     * @return
     */
    private static Object convertValType(Object value, Class<?> fieldTypeClass) {
        Object retVal = null;

        if (Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }

}

