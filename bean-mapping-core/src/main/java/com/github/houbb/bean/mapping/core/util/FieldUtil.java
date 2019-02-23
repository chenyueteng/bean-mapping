package com.github.houbb.bean.mapping.core.util;

import com.github.houbb.bean.mapping.core.api.core.field.DefaultField;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.PrimitiveUtil;

import java.lang.reflect.Modifier;

/**
 * 字段工具类
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public final class FieldUtil {

    private FieldUtil(){}

    /**
     * 是否可以设置
     * @param sourceField 原始字段
     * @param targetField 目标字段
     * @return 结果
     */
    public static boolean isAssignable(final DefaultField sourceField, final DefaultField targetField) {
        // 如果有任何一个字段为 null，直接返回。说明没有对应的映射字段信息
        if(ObjectUtil.isNull(sourceField)
                || ObjectUtil.isNull(targetField)) {
            return false;
        }

        // 如果有任何一个 condition 为 false，直接返回
        if(!sourceField.isMappingCondition()
            || !targetField.isMappingCondition()) {
            return false;
        }

        // 如果 target 的字段为 final 则不进行设置
        if(Modifier.isFinal(targetField.getModifiers())) {
            return false;
        }

        final Class<?> sourceType = sourceField.getMappingType();
        final Class<?> targetType = targetField.getMappingType();
        if(targetType.isAssignableFrom(sourceType)) {
            return true;
        }

        // 基础类型的判断
        Class resolvedPrimitive;
        if (sourceType.isPrimitive()) {
            resolvedPrimitive = PrimitiveUtil.getPrimitiveType(targetType);
            return sourceType == resolvedPrimitive;
        } else {
            resolvedPrimitive = PrimitiveUtil.getPrimitiveType(targetType);
            return resolvedPrimitive != null && sourceType.isAssignableFrom(resolvedPrimitive);
        }
    }

}
