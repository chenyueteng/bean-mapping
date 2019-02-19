/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.util;

import com.github.houbb.bean.mapping.api.core.IBeanMpping;
import com.github.houbb.bean.mapping.core.factory.BeanMappingFactory;

/**
 * 对象工具类
 * @author binbin.hou
 * date 2019/2/19
 * @since 0.0.1
 */
public final class BeanUtil {

    /**
     * 复制属性
     * @param source 原始对象
     * @param target 目标对象
     */
    public static void copyProperties(final Object source, Object target) {
        IBeanMpping beanMpping = BeanMappingFactory.getInstance();
        beanMpping.mapping(source, target);
    }

}
