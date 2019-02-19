/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.api.core;

/**
 * bean 映射接口
 * @author binbin.hou
 * date 2019/2/19
 * @since 0.0.1
 */
public interface IBeanMpping {

    /**
     * 对象属性映射
     * 1. 将二者名称相同且类型相同的属性进行赋值。
     * @param source 原始对象
     * @param target 目标对象
     */
    void mapping(final Object source, Object target);

}
