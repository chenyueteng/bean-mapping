/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.api.core;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.api.annotation.BeanMappingEntry;
import com.github.houbb.bean.mapping.api.core.*;
import com.github.houbb.bean.mapping.core.api.core.context.DefaultContext;
import com.github.houbb.bean.mapping.core.api.core.field.DefaultField;
import com.github.houbb.bean.mapping.core.exception.BeanMappingRuntimeException;
import com.github.houbb.bean.mapping.core.support.convert.DefaultFieldConvert;
import com.github.houbb.bean.mapping.core.util.ClassTypeUtil;
import com.github.houbb.bean.mapping.core.util.FieldUtil;
import com.github.houbb.bean.mapping.core.util.MappingClassUtil;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
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

                // 注解 @BeanMapping/@BeanMappingEntry 相关信息的处理
                // 如果没有注解的处理，就和普通的 BeanUtils 功能是一样的。
                DefaultField sourceDefaultField = (DefaultField) sourceField;
                DefaultField targetDefaultField = (DefaultField) targetField;
                fillField(defaultContext, sourceDefaultField);
                fillField(defaultContext, targetDefaultField);

                // 这里有个问题：如果属性为对象/列表/map 等等，直接设置，那么是浅拷贝。
                if (FieldUtil.isAssignable(sourceDefaultField, targetDefaultField)) {
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
     * TODO: 这里转换之后需要再次执行值得 fill 如果报错，则直接终止赋值。
     * @param defaultContext 执行上下文
     * @param defaultField   默认的字段信息
     * @since 0.1.0 处理 @BeanMapping 信息
     * @since 0.2.0 处理 @BeanMappingEntry 字段信息
     */
    private void fillField(final DefaultContext defaultContext,
                           final DefaultField defaultField) {
        try {
            final Field field = defaultField.getField();
            // @BeanMappingEntry 注解信息处理
            if(field.isAnnotationPresent(BeanMappingEntry.class)) {
                this.handleSourceDefaultField(defaultField);
            }

            // BeanMapping 注解信息的处理
            if (field.isAnnotationPresent(BeanMapping.class)) {
                BeanMapping beanMapping = field.getAnnotation(BeanMapping.class);

                // 条件
                Class<? extends ICondition> conditionClass = beanMapping.condition();
                if (!ICondition.class.equals(conditionClass)) {
                    ICondition condition = conditionClass.newInstance();
                    boolean result = condition.condition(defaultContext);
                    defaultField.setMappingCondition(result);
                }

                // 转换-会受到 @BeanMappingEntry 的转换结果影响
                Class<? extends IConvert> convertClass = beanMapping.convert();
                if (!IConvert.class.equals(convertClass)) {
                    IConvert convert = convertClass.newInstance();
                    // 这里是将转换后的值，放在 mappingValue 中。
                    Object mappingValue = convert.convert(defaultContext);
                    defaultField.setMappingValue(mappingValue);

                    // 如果转换之后的值不是 null，则设置对应的值类型
                    // 如果转换的值为 null，则依然使用原来的值类型。不影响赋值。
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
     * 是否需要进行明细处理的字段
     * 1. 基本类型
     * 2. 数组/集合/map 中放的是基本类型
     * 3. 枚举类型
     * 4. jdk 中的元素类型
     * 4. 不包含 @BeanMapping/@BeanMappingEntry 信息的 bean。
     *
     * 对 mappingValue 进行相关的处理
     * 1. 如果不作处理，直接返回原来的值即可。
     * 2. 如果需要做处理，则将所有的处理值结果放在 {@link DefaultField#setMappingValue(Object)} 属性上。
     * @param sourceDefaultField 原始的字段信息
     */
    private void handleSourceDefaultField(final DefaultField sourceDefaultField) {
        final Field field = sourceDefaultField.getField();
        final Class<?> fieldType = field.getType();

        //Map Entry 的赋值处理
        //1. 如果需要处理，会变得比较复杂。暂时不做处理
        //2. TODO: 后期可以针对 key/value 进行分别的处理。
        if(ClassTypeUtil.isMapClass(fieldType)) {
            return;
        }
        // JDK 本身的对象类，直接不做处理。
        if(ClassTypeUtil.isJdkClass(fieldType)) {
            return;
        }

        // 实际存放映射值的地方。
        Object mappingValue = sourceDefaultField.getMappingValue();
        if(ObjectUtil.isNull(mappingValue)) {
            // 当前对象值为 null，则不需要进行处理。
            return;
        }

        // 数组
        if(ClassTypeUtil.isArrayClass(fieldType)) {
            Object[] arrays = (Object[]) mappingValue;
            if(ArrayUtil.isNotEmpty(arrays)) {
                // 针对数组信息进行处理
                // 对于数组值得处理，直接递归处理即可。
                // 这里可以利用递归获取值，然后使用一个子类存放值。
                List<Object> resultList = new ArrayList<Object>();
                for(Object entry : arrays) {
                    // 单独处理每一个明细

                }
                mappingValue = resultList;
            }
        }

        // Iterable 字段
        if(ClassTypeUtil.isIterableClass(fieldType)) {
            Iterable<?> iterableMappingValue = (Iterable<?>) mappingValue;
            Iterator iterator = iterableMappingValue.iterator();
            while(iterator.hasNext()) {
                Object entry = iterator.next();

            }
        }

        // 自定义 java 对象
        if(ClassTypeUtil.isJavaBeanClass(fieldType)) {
            // 对于单个的直接递归调用即可。
            // 不包含任何注解，直接赋值(不做任何处理)，不是基础属性，则做进一步的赋值。
            // 是否需要调用一次属性的复制？将 object 的值，使用 BeanUtil.copy 再赋值一遍，这就变成了深度拷贝。
            // 这里需要递归循环当前对象的所有 Field ，去处理对应的信息。

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
