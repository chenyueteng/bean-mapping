/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.util;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 基本类型工具类
 * @author binbin.hou
 * date 2019/2/19
 */
public final class PrimitiveUtil {

    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<>(8);

    static {
        primitiveWrapperTypeMap.put(Boolean.class, Boolean.TYPE);
        primitiveWrapperTypeMap.put(Byte.class, Byte.TYPE);
        primitiveWrapperTypeMap.put(Character.class, Character.TYPE);
        primitiveWrapperTypeMap.put(Double.class, Double.TYPE);
        primitiveWrapperTypeMap.put(Float.class, Float.TYPE);
        primitiveWrapperTypeMap.put(Integer.class, Integer.TYPE);
        primitiveWrapperTypeMap.put(Long.class, Long.TYPE);
        primitiveWrapperTypeMap.put(Short.class, Short.TYPE);
    }

    /**
     * 获取对应的基本类型
     * @param classType class 类型
     * @return 基本类型
     */
    public static Class<?> getPrimitiveType(final Class<?> classType) {
        return primitiveWrapperTypeMap.get(classType);
    }

}
