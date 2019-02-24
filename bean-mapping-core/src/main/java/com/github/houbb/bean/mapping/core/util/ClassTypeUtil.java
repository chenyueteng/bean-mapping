package com.github.houbb.bean.mapping.core.util;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

/**
 * class 类型工具类
 * @author binbin.hou
 * date 2019/2/24
 * @since 0.2.0
 */
public class ClassTypeUtil {

    /**
     * 常见的基础对象类型
     */
    private static final Class[] BASE_TYPE_CLASS = new Class[]{
            String.class, Boolean.class, Character.class, Byte.class, Short.class,
            Integer.class, Long.class, Float.class, Double.class, Void.class, Object.class, Class.class
    };

    /**
     * 是否为 map class 类型
     *
     * @param clazz 对象类型
     * @return 是否为 map class
     */
    public static boolean isMapClass(final Class<?> clazz) {
        return Map.class.equals(clazz);
    }

    /**
     * 是否为 数组 class 类型
     *
     * @param clazz 对象类型
     * @return 是否为 数组 class
     */
    public static boolean isArrayClass(final Class<?> clazz) {
        return clazz.isArray();
    }

    /**
     * 是否为 Collection class 类型
     *
     * @param clazz 对象类型
     * @return 是否为 Collection class
     */
    public static boolean isCollectionClass(final Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    /**
     * 是否为 Iterable class 类型
     *
     * @param clazz 对象类型
     * @return 是否为 数组 class
     */
    public static boolean isIterableClass(final Class<?> clazz) {
        return Iterable.class.isAssignableFrom(clazz);
    }

    /**
     * 是否为基本类型
     * 1. 8大基本类型
     * 2. 常见的值类型
     *
     * @param clazz 对象类型
     * @return 是否为基本类型
     */
    public static boolean isBaseClass(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            return true;
        }
        for (Class baseClazz : BASE_TYPE_CLASS) {
            if (baseClazz.equals(clazz)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否为抽象类
     *
     * @param clazz 类
     * @return 是否为抽象类
     * @since 0.0.2
     */
    private static boolean isAbstractClass(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    /**
     * 是否为标准的类<br>
     * 这个类必须：
     *
     * <pre>
     * 0、不为 null
     * 1、非接口
     * 2、非抽象类
     * 3、非Enum枚举
     * 4、非数组
     * 5、非注解
     * 6、非原始类型（int, long等）
     * 7、非集合 Iterable
     * 8、非 Map.clas
     * 9、非 JVM 生成类
     * </pre>
     *
     * @param clazz 类
     * @return 是否为标准类
     * @since 0.0.2
     */
    public static boolean isJavaBeanClass(Class<?> clazz) {
        return null != clazz
                && !clazz.isInterface()
                && !isAbstractClass(clazz)
                && !clazz.isEnum()
                && !clazz.isArray()
                && !clazz.isAnnotation()
                && !clazz.isSynthetic()
                && !clazz.isPrimitive()
                && isIterableClass(clazz);
    }

    /**
     * 判断一个类是JDK 自带的类型
     * jdk 自带的类，classLoader 是为空的。
     * @param clazz 类
     * @return 是否为 java 类
     */
    public static boolean isJdkClass(Class<?> clazz) {
        return clazz != null && clazz.getClassLoader() == null;
    }

}
