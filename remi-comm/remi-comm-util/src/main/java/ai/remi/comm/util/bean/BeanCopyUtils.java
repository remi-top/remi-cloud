package ai.remi.comm.util.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc BeanCopyUtils
 */
public class BeanCopyUtils {
    
    /**
     * 浅拷贝list
     *
     * @param source 数据源
     * @param clazz  对象
     * @return LIST
     */
    public static List copyListProperties(List source, Class clazz) {
        List target = new ArrayList();
        source.forEach(o -> {
            try {
                target.add(JSONObject.parseObject(JSONObject.toJSONString(o), clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return target;
    }
    
    /**
     * 源对象和目标对象浅拷贝 PS：目标对象和源对象中的属性名称需一至，否则无法copy
     *
     * @param sourceObj 源对象
     * @param targetObj 目标对象
     */
    public static void copyPropertiesThird(Object sourceObj, Object targetObj) {
        BeanUtils.copyProperties(sourceObj, targetObj);
    }
    
    public static <T> T copyPropertiesThird(Object sourceObj, Class<T> calzz) {
        if (Objects.isNull(sourceObj) || Objects.isNull(calzz)) {
            return null;
        }
        T c = null;
        try {
            // c = calzz.newInstance();
            c = calzz.getDeclaredConstructor().newInstance();
            BeanCopyUtils.copyPropertiesThird(sourceObj, c);
        } catch (Exception e) {
            System.out.println("BeanCopyUtils -> copyPropertiesThird error：" + e);
        }
        return c;
    }
    
    /**
     * 转换list泛型
     *
     * @param source 数据源
     * @param clazz  对象
     * @return LIST
     */
    public static <T> List<T> coverList(List source, Class<T> clazz) {
        List<T> res = new ArrayList<>();
        source.forEach(o -> res.add(BeanCopyUtils.copyPropertiesThird(o, clazz)));
        return res;
    }


    public static Map<String, Object> entityToMap(Object object) {
        Map<String, Object> reMap = new HashMap();
        if (object == null) {
            return null;
        } else {
            List<Field> fields = new ArrayList();
            List<String> fieldsName = new ArrayList();

            for(Class tempClass = object.getClass(); tempClass != null; tempClass = tempClass.getSuperclass()) {
                fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            }

            List<Field> childFields = Arrays.asList(object.getClass().getDeclaredFields());
            Iterator var6 = childFields.iterator();

            Field field;
            while(var6.hasNext()) {
                field = (Field)var6.next();
                fieldsName.add(field.getName());
            }

            try {
                var6 = fields.iterator();

                while(var6.hasNext()) {
                    field = (Field)var6.next();

                    try {
                        Field f;
                        Object o;
                        if (fieldsName.contains(field.getName())) {
                            f = object.getClass().getDeclaredField(field.getName());
                            f.setAccessible(true);
                            o = f.get(object);
                            reMap.put(field.getName(), o);
                        } else {
                            f = object.getClass().getSuperclass().getDeclaredField(field.getName());
                            f.setAccessible(true);
                            o = f.get(object);
                            reMap.put(field.getName(), o);
                        }
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }
            } catch (SecurityException var11) {
                var11.printStackTrace();
            }

            return reMap;
        }
    }
}