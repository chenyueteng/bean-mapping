/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.api.core;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.api.core.*;
import com.github.houbb.bean.mapping.core.api.core.context.DefaultContext;
import com.github.houbb.bean.mapping.core.api.core.field.DefaultField;
import com.github.houbb.bean.mapping.core.exception.BeanMappingRuntimeException;
import com.github.houbb.bean.mapping.core.support.convert.DefaultFieldConvert;
import com.github.houbb.bean.mapping.core.util.MappingFieldUtil;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 默认的对象映射实现
 *
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
        List<Field> targetFieldList = ClassUtil.getAllFieldList(target.getClass());

        //2.1 source 列表的过滤
        List<IField> sourceDefaultFieldList = (List<IField>) CollectionUtil.buildCollection(sourceFieldList,
                new DefaultFieldConvert(source));
        //2.2 target 列表的过滤
        List<IField> targetDefaultFieldList = (List<IField>) CollectionUtil.buildCollection(targetFieldList,
                new DefaultFieldConvert(target));

        // 执行上下文的处理
        DefaultContext defaultContext = new DefaultContext();
        defaultContext.setSourceObject(source);
        defaultContext.setTargetObject(target);
        defaultContext.setAllSourceFields(sourceDefaultFieldList);
        defaultContext.setAllTargetFields(targetDefaultFieldList);

        Map<String, IField> targetFieldMap = buildFieldMap(targetDefaultFieldList);
        //3. 循环设置属性
        try {
            for (IField sourceField : sourceDefaultFieldList) {
                // 判断可以设置，那么将属性的值直接设置过来
                IField targetField = targetFieldMap.get(sourceField.getMappingName());
                if (ObjectUtil.isNull(targetField)) {
                    continue;
                }

                // 设置上下文信息
                defaultContext.setCurrentSourceField(sourceField);
                defaultContext.setCurrentTargetField(targetField);
                DefaultField sourceDefaultField = (DefaultField) sourceField;
                DefaultField targetDefaultField = (DefaultField) targetField;

                // 注解相关信息的处理
                fillField(defaultContext, sourceDefaultField);
                fillField(defaultContext, targetDefaultField);

                // 这里有个问题：如果属性为对象/列表/map 等等，直接设置，那么是浅拷贝。
                if (MappingFieldUtil.isAssignable(sourceDefaultField, targetDefaultField)) {
                    Object sourceValue = sourceDefaultField.getMappingValue();
                    targetDefaultField.getField().set(target, sourceValue);
                }
            }
        } catch (IllegalAccessException e) {
            throw new BeanMappingRuntimeException(e);
        }
    }

    /**
     * 填充字段信息
     *
     * @param defaultContext 执行上下文
     * @param defaultField   默认的字段信息
     * @since 0.1.0
     */
    private void fillField(final DefaultContext defaultContext,
                           final DefaultField defaultField) {
        try {
            final Field field = defaultField.getField();
            // 注解信息的处理
            if (field.isAnnotationPresent(BeanMapping.class)) {
                BeanMapping beanMapping = field.getAnnotation(BeanMapping.class);

                // 条件
                Class<? extends ICondition> conditionClass = beanMapping.condition();
                if (!ICondition.class.equals(conditionClass)) {
                    ICondition condition = conditionClass.newInstance();
                    boolean result = condition.condition(defaultContext);
                    defaultField.setMappingCondition(result);
                }

                // 转换
                Class<? extends IConvert> convertClass = beanMapping.convert();
                if (!IConvert.class.equals(convertClass)) {
                    IConvert convert = convertClass.newInstance();
                    Object mappingValue = convert.convert(defaultContext);
                    defaultField.setMappingValue(mappingValue);

                    // 如果转换之后的值不是 null，则设置对应的值类型
                    if (ObjectUtil.isNotNull(mappingValue)) {
                        defaultField.setMappingType(mappingValue.getClass());
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanMappingRuntimeException(e);
        }
    }

    /**
     * 构建字段的 map 信息
     *
     * @param fieldList 字段列表
     * @return map 信息
     * @since 0.1.0
     */
    @SuppressWarnings("all")
    private Map<String, IField> buildFieldMap(final List<IField> fieldList) {
        if (CollectionUtil.isEmpty(fieldList)) {
            return Collections.EMPTY_MAP;
        }

        Map<String, IField> fieldMap = new HashMap<>(fieldList.size());
        for (IField field : fieldList) {
            fieldMap.put(field.getMappingName(), field);
        }
        return fieldMap;
    }

}
