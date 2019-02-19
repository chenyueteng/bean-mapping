/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.util;

import com.github.houbb.bean.mapping.core.exception.BeanMappingRuntimeException;

/**
 * 参数工具类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class ArgUtil {

    /**
     * 断言不为 null
     * @param object 对象
     * @param tips 提示
     */
    public static void notNull(final Object object, final String tips) {
        if(ObjectUtil.isNull(object)) {
            throw new BeanMappingRuntimeException(tips);
        }
    }

}
