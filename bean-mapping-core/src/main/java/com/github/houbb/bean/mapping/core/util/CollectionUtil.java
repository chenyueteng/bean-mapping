/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.util;

import java.util.Collection;

/**
 * 集合工具类
 * @author binbin.hou
 * date 2019/2/19
 * @since 0.0.1
 */
public final class CollectionUtil {

    /**
     * 是否为空
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(final Collection<?> collection) {
        return null == collection
                || collection.size() == 0;
    }

    public static boolean isNotEmpty(final Collection<?> collection) {
        return !isEmpty(collection);
    }

}
