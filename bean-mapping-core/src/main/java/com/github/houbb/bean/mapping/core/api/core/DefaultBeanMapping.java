/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.api.core;

import com.github.houbb.bean.mapping.api.core.IBeanMpping;
import com.github.houbb.bean.mapping.core.exception.BeanMappingRuntimeException;
import com.github.houbb.bean.mapping.core.util.ArgUtil;
import com.github.houbb.bean.mapping.core.util.ClassUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 默认的对象映射实现
 * @author binbin.hou
 * date 2019/2/19
 * @since 0.0.1
 */
public class DefaultBeanMapping implements IBeanMpping {
    @Override
    public void mapping(Object source, Object target) {
        //1. 参数校验
        ArgUtil.notNull(source, "Source not allow null here.");
        ArgUtil.notNull(target, "Target not allow null here.");

        //2. 获取属性列表
        List<Field> sourceFieldList = ClassUtil.getAllFieldList(source.getClass());
        Map<String, Field> targetFieldMap = ClassUtil.getAllFieldLMap(target.getClass());

        //2.1 source 列表的过滤
        //2.2 target 列表的过滤

        //3. 循环设置属性
        try {
            for(Field sourceField : sourceFieldList) {
                // 判断可以设置，那么将属性的值直接设置过来
                Field targetField = targetFieldMap.get(sourceField.getName());

                // 这里有个问题：如果属性为对象/列表/map 等等，直接设置，那么是浅拷贝。
                if(ClassUtil.isAssignable(sourceField, targetField)) {
                    Object sourceValue = sourceField.get(source);
                    targetField.set(target, sourceValue);
                }
            }
        } catch (IllegalAccessException e) {
            throw new BeanMappingRuntimeException(e);
        }
    }


}
