/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 方法直接来自 hutool
 * @author binbin.hou
 * date 2018/11/8 16:20
 * @since 0.0.1
 */
public final class ClassUtil {

    private ClassUtil(){}

    /**
     * 获取类所有的字段信息
     * ps: 这个方法有个问题 如果子类和父类有相同的字段 会不会重复
     * 1. 还会获取到 serialVersionUID 这个字段。
     * @param clazz 类
     * @return 字段列表
     */
    public static List<Field> getAllFieldList(final Class clazz) {
        // 构建并且缓存
        List<Field> fieldList = new ArrayList<>() ;
        Class tempClass = clazz;
        while (tempClass != null) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }

        for(Field field : fieldList) {
            field.setAccessible(true);
        }
        return fieldList;
    }

    /**
     * 获取类所有的字段信息 map
     * ps: 这个方法有个问题 如果子类和父类有相同的字段 会不会重复
     * 1. 还会获取到 serialVersionUID 这个字段。
     * @param clazz 类
     * @return 字段列表 map
     */
    public static Map<String, Field> getAllFieldLMap(final Class clazz) {
        List<Field> fieldList = ClassUtil.getAllFieldList(clazz);

        Map<String, Field> map = new LinkedHashMap<>();
        for(Field field : fieldList) {
            map.put(field.getName(), field);
        }
        return map;
    }

    /**
     * 是否为抽象类
     *
     * @param clazz 类
     * @return 是否为抽象类
     */
    private static boolean isAbstract(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    /**
     * 是否为标准的类<br>
     * 这个类必须：
     *
     * <pre>
     * 1、非接口
     * 2、非抽象类
     * 3、非Enum枚举
     * 4、非数组
     * 5、非注解
     * 6、非原始类型（int, long等）
     * </pre>
     *
     * @param clazz 类
     * @return 是否为标准类
     */
    public static boolean isNormalClass(Class<?> clazz) {
        return null != clazz
                && !clazz.isInterface()
                && !isAbstract(clazz)
                && !clazz.isEnum()
                && !clazz.isArray()
                && !clazz.isAnnotation()
                && !clazz.isSynthetic()
                && !clazz.isPrimitive();
    }

    /**
     * bean 转换为 map
     * @param bean 原始对象
     * @return 结果
     */
    public static Map<String, Object> beanToMap(Object bean) {
        try {
            Map<String, Object> map = new LinkedHashMap<>();
            List<Field> fieldList = ClassUtil.getAllFieldList(bean.getClass());

            for (Field field : fieldList) {
                final String fieldName = field.getName();
                final Object fieldValue = field.get(bean);
                map.put(fieldName, fieldValue);
            }
            return map;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否为Bean对象<br>
     * 判定方法是是否存在只有一个参数的setXXX方法
     *
     * @param clazz 待测试类
     * @return 是否为Bean对象
     */
    public static boolean isBean(Class<?> clazz) {
        if (ClassUtil.isNormalClass(clazz)) {
            final Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getParameterTypes().length == 1 && method.getName().startsWith("set")) {
                    // 检测包含标准的setXXX方法即视为标准的JavaBean
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否可以设置
     * @param sourceField 原始字段
     * @param targetField 目标字段
     * @return 结果
     */
    public static boolean isAssignable(final Field sourceField, final Field targetField) {
        // 如果有任何一个字段为空，直接返回
        if(ObjectUtil.isNull(sourceField)
                || ObjectUtil.isNull(targetField)) {
            return false;
        }

        // 如果 target 的字段为 final 则不进行设置
        if(Modifier.isFinal(targetField.getModifiers())) {
            return false;
        }

        final Class<?> sourceType = sourceField.getType();
        final Class<?> targetType = targetField.getType();
        if(sourceType.isAssignableFrom(targetType)) {
            return true;
        }

        // 基础类型的判断
        Class resolvedPrimitive;
        if (sourceType.isPrimitive()) {
            resolvedPrimitive = PrimitiveUtil.getPrimitiveType(targetType);
            if (sourceType == resolvedPrimitive) {
                return true;
            }
        } else {
            resolvedPrimitive = PrimitiveUtil.getPrimitiveType(targetType);
            if (resolvedPrimitive != null && sourceType.isAssignableFrom(resolvedPrimitive)) {
                return true;
            }
        }

        return false;
    }

}
