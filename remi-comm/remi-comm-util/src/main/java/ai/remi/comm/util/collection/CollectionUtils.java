package ai.remi.comm.util.collection;

import ai.remi.comm.util.array.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionUtils {
    public static final String DESC = "desc";
    public static final String ASC = "asc";

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return 若集合为null, 或集合大小为0, 则返回true, 否则返回false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * Map是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 集合是否不为空
     *
     * @param collection 集合
     * @return 若集合不为null, 而且集合大小不为0, 则返回true, 否则返回false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * Map是否不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 参数转换为List集合
     *
     * @param objs 参数对象
     * @return 返回泛型
     */
    public static <E> List<E> asList(E... objs) {
        return Arrays.asList(objs);
    }

    /**
     * 参数集合转换为List集合
     *
     * @param collection 集合参数
     * @return 返回list泛型
     */
    public static <E> List<E> asList(Collection<E> collection) {
        if (collection == null) {
            return null;
        }
        if (collection instanceof List) {
            return (List<E>) collection;
        }
        return new ArrayList<E>(collection);
    }

    /**
     * 参数转换为Set集合
     *
     * @param objs 参数对象
     * @return 返回set泛型
     */
    public static <E> Set<E> asSet(E... objs) {
        return new HashSet<E>(Arrays.asList(objs));
    }

    /**
     * 参数转换为Set集合
     *
     * @param collection 集合参数
     * @return 返回set泛型
     */
    public static <E> Set<E> asSet(Collection<E> collection) {
        if (collection == null) {
            return null;
        }
        if (collection instanceof Set) {
            return (Set<E>) collection;
        }
        return new HashSet<E>(collection);
    }

    /**
     * 将集合转换为数组
     *
     * @param collection 集合
     * @returns 返回数组
     */
    public static <E> E[] toArray(Collection<E> collection) {
        return ArrayUtils.asArray(collection);
    }

    /**
     * 创建ArrayList实例
     *
     * @return 返回list
     */
    public static <E> List<E> newList() {
        return new ArrayList<E>();
    }

    /**
     * 创建一个指定容量的ArrayList实例
     *
     * @param size 大小
     * @return 返回list
     */
    public static <E> List<E> newList(int size) {
        return new ArrayList<E>(size);
    }

    /**
     * 创建一个HashSet实例
     *
     * @return 返回set泛型
     */
    public static <E> Set<E> newSet() {
        return new HashSet<E>(16, .75f);
    }

    /**
     * 创建一个指定容量大小的HashSet实例
     *
     * @param initialCapacity 初始容量
     * @param loadFactor      加载因子
     * @return 返回set
     */
    public static <E> Set<E> newSet(int initialCapacity, double loadFactor) {
        return new HashSet<E>(initialCapacity, (float) loadFactor);
    }

    /**
     * 获取map的最小key的value
     *
     * @param map 传入map的值
     * @return String
     */
    public static String getMinKeysValue(Map<Integer, String> map) {
        if (map == null || map.size() < 1) {
            return new String();
        }
        int key = map.keySet().stream().min(Comparator.comparing(Function.identity())).get();
        return map.get(key);
    }

    /**
     * 对list中的元素按升序排列.
     *
     * @param list  排序集合
     * @param field 排序字段
     * @return
     */
    public static List<?> sort(List<?> list, final String field) {
        return sort(list, field, null);
    }

    /**
     * 对list中的元素进行排序.
     *
     * @param list  排序集合
     * @param field 排序字段
     * @param sort  排序方式: SortList.DESC(降序) SortList.ASC(升序).
     */
    @SuppressWarnings("unchecked")
    public static List<?> sort(List<?> list, final String field,
                               final String sort) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Field f = a.getClass().getDeclaredField(field);
                    f.setAccessible(true);
                    Class<?> type = f.getType();

                    if (type == int.class) {
                        ret = Integer.compare(f.getInt(a), f
                                .getInt(b));
                    } else if (type == double.class) {
                        ret = Double.compare(f.getDouble(a), f
                                .getDouble(b));
                    } else if (type == long.class) {
                        ret = Long.compare(f.getLong(a), f
                                .getLong(b));
                    } else if (type == float.class) {
                        ret = Float.compare(f.getFloat(a), f
                                .getFloat(b));
                    } else if (type == Date.class) {
                        ret = ((Date) f.get(a)).compareTo((Date) f.get(b));
                    } else if (isImplementsOf(type, Comparable.class)) {
                        ret = ((Comparable) f.get(a)).compareTo(f
                                .get(b));
                    } else {
                        ret = String.valueOf(f.get(a)).compareTo(
                                String.valueOf(f.get(b)));
                    }

                } catch (SecurityException | NoSuchFieldException | IllegalAccessException |
                         IllegalArgumentException e) {
                    e.printStackTrace();
                }
                if (sort != null && sort.equalsIgnoreCase(DESC)) {
                    return -ret;
                } else {
                    return ret;
                }

            }
        });
        return list;
    }

    /**
     * 对list中的元素按fields和sorts进行排序,
     * fields[i]指定排序字段,sorts[i]指定排序方式.如果sorts[i]为空则默认按升序排列.
     *
     * @param list
     * @param fields
     * @param sorts
     */
    @SuppressWarnings("unchecked")
    public static List<?> sort(List<?> list, String[] fields, String[] sorts) {
        if (fields != null && fields.length > 0) {
            for (int i = fields.length - 1; i >= 0; i--) {
                final String field = fields[i];
                String tmpSort = ASC;
                if (sorts != null && sorts.length > i && sorts[i] != null) {
                    tmpSort = sorts[i];
                }
                final String sort = tmpSort;
                Collections.sort(list, new Comparator() {
                    public int compare(Object a, Object b) {
                        int ret = 0;
                        try {
                            Field f = a.getClass().getDeclaredField(field);
                            f.setAccessible(true);
                            Class<?> type = f.getType();
                            if (type == int.class) {
                                ret = ((Integer) f.getInt(a))
                                        .compareTo(f.getInt(b));
                            } else if (type == double.class) {
                                ret = ((Double) f.getDouble(a))
                                        .compareTo(f.getDouble(b));
                            } else if (type == long.class) {
                                ret = ((Long) f.getLong(a)).compareTo(f
                                        .getLong(b));
                            } else if (type == float.class) {
                                ret = ((Float) f.getFloat(a))
                                        .compareTo(f.getFloat(b));
                            } else if (type == Date.class) {
                                ret = ((Date) f.get(a)).compareTo((Date) f
                                        .get(b));
                            } else if (isImplementsOf(type, Comparable.class)) {
                                ret = ((Comparable) f.get(a))
                                        .compareTo(f.get(b));
                            } else {
                                ret = String.valueOf(f.get(a)).compareTo(
                                        String.valueOf(f.get(b)));
                            }

                        } catch (SecurityException | NoSuchFieldException | IllegalArgumentException |
                                 IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        if (sort != null && sort.equalsIgnoreCase(DESC)) {
                            return -ret;
                        } else {
                            return ret;
                        }
                    }
                });
            }
        }
        return list;
    }

    /**
     * 默认按正序排列
     *
     * @param list
     * @param method
     */
    public static List<?> sortByMethod(List<?> list, final String method) {
        return sortByMethod(list, method, null);
    }

    @SuppressWarnings("unchecked")
    public static List<?> sortByMethod(List<?> list, final String method,
                                       final String sort) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m = a.getClass().getMethod(method, null);
                    m.setAccessible(true);
                    Class<?> type = m.getReturnType();
                    if (type == int.class) {
                        ret = ((Integer) m.invoke(a, null))
                                .compareTo((Integer) m.invoke(b, null));
                    } else if (type == double.class) {
                        ret = ((Double) m.invoke(a, null)).compareTo((Double) m
                                .invoke(b, null));
                    } else if (type == long.class) {
                        ret = ((Long) m.invoke(a, null)).compareTo((Long) m
                                .invoke(b, null));
                    } else if (type == float.class) {
                        ret = ((Float) m.invoke(a, null)).compareTo((Float) m
                                .invoke(b, null));
                    } else if (type == Date.class) {
                        ret = ((Date) m.invoke(a, null)).compareTo((Date) m
                                .invoke(b, null));
                    } else if (isImplementsOf(type, Comparable.class)) {
                        ret = ((Comparable) m.invoke(a, null))
                                .compareTo(m.invoke(b, null));
                    } else {
                        ret = String.valueOf(m.invoke(a, null)).compareTo(
                                String.valueOf(m.invoke(b, null)));
                    }

                    if (isImplementsOf(type, Comparable.class)) {
                        ret = ((Comparable) m.invoke(a, null))
                                .compareTo(m.invoke(b, null));
                    } else {
                        ret = String.valueOf(m.invoke(a, null)).compareTo(
                                String.valueOf(m.invoke(b, null)));
                    }

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ne) {
                    ne.printStackTrace();
                }

                if (sort != null && sort.toLowerCase().equals(DESC)) {
                    return -ret;
                } else {
                    return ret;
                }
            }
        });
        return list;
    }

    @SuppressWarnings("unchecked")
    public static List<?> sortByMethod(List<?> list, final String methods[],
                                       final String sorts[]) {
        if (methods != null && methods.length > 0) {
            for (int i = methods.length - 1; i >= 0; i--) {
                final String method = methods[i];
                String tmpSort = ASC;
                if (sorts != null && sorts.length > i && sorts[i] != null) {
                    tmpSort = sorts[i];
                }
                final String sort = tmpSort;
                Collections.sort(list, new Comparator() {
                    public int compare(Object a, Object b) {
                        int ret = 0;
                        try {
                            Method m = a.getClass().getMethod(method, null);
                            m.setAccessible(true);
                            Class<?> type = m.getReturnType();
                            if (type == int.class) {
                                ret = ((Integer) m.invoke(a, null))
                                        .compareTo((Integer) m.invoke(b, null));
                            } else if (type == double.class) {
                                ret = ((Double) m.invoke(a, null))
                                        .compareTo((Double) m.invoke(b, null));
                            } else if (type == long.class) {
                                ret = ((Long) m.invoke(a, null))
                                        .compareTo((Long) m.invoke(b, null));
                            } else if (type == float.class) {
                                ret = ((Float) m.invoke(a, null))
                                        .compareTo((Float) m.invoke(b, null));
                            } else if (type == Date.class) {
                                ret = ((Date) m.invoke(a, null))
                                        .compareTo((Date) m.invoke(b, null));
                            } else if (isImplementsOf(type, Comparable.class)) {
                                ret = ((Comparable) m.invoke(a, null))
                                        .compareTo(m.invoke(b,
                                                null));
                            } else {
                                ret = String.valueOf(m.invoke(a, null))
                                        .compareTo(
                                                String.valueOf(m
                                                        .invoke(b, null)));
                            }

                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ne) {
                            ne.printStackTrace();
                        }

                        if (sort != null && sort.toLowerCase().equals(DESC)) {
                            return -ret;
                        } else {
                            return ret;
                        }
                    }
                });
            }
        }
        return list;
    }

    /**
     * 判断对象实现的所有接口中是否包含szInterface
     *
     * @param clazz
     * @param szInterface
     */
    public static boolean isImplementsOf(Class<?> clazz, Class<?> szInterface) {
        boolean flag = false;

        Class<?>[] face = clazz.getInterfaces();
        for (Class<?> c : face) {
            if (c == szInterface) {
                flag = true;
            } else {
                flag = isImplementsOf(c, szInterface);
            }
        }

        if (!flag && null != clazz.getSuperclass()) {
            return isImplementsOf(clazz.getSuperclass(), szInterface);
        }
        return flag;
    }

    /**
     * 根据集合的某个指定字段去重
     * 使用方式：
     * list.stream().filter(CollectionUtils.distinctByKey(RoleVO::getId)).collect(Collectors.toList());
     *
     * @param keyExtractor 需要去重的字段
     * @param <T>
     * @return java.util.function.Predicate<T>
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
