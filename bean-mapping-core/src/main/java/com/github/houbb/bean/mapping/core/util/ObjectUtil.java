/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.util;

/**
 * 对象工具类
 * @author binbin.hou
 * date 2019/2/19
 * @since 0.0.1
 */
public final class ObjectUtil {

    private ObjectUtil(){}

    /**
     * 是否为 null
     * @param object 对象
     * @return 是否为 null
     */
    public static boolean isNull(final Object object) {
        return null == object;
    }

    /**
     * 是否不为 null
     * @param object 对象
     * @return 是否不为 null
     */
    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }

}
