package com.github.houbb.bean.mapping.core.support.convert;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.api.core.IField;
import com.github.houbb.bean.mapping.core.api.core.field.DefaultField;
import com.github.houbb.bean.mapping.core.exception.BeanMappingRuntimeException;
import com.github.houbb.heaven.support.handler.AbstractHandler;
import com.github.houbb.heaven.util.lang.StringUtil;

import java.lang.reflect.Field;

/**
 * 默认字段的转换类型
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class DefaultFieldConvert extends AbstractHandler<Field, IField> {

    /**
     * 原始对象信息
     */
    private Object object;

    public DefaultFieldConvert(final Object object) {
        this.object = object;
    }

    @Override
    protected IField doHandle(Field field) {
        try {
            DefaultField defaultField = new DefaultField();
            final Object fieldValue = field.get(object);
            final String fieldName = field.getName();
            final Class<?> fieldType = field.getType();
            defaultField.setModifiers(field.getModifiers());
            defaultField.setName(fieldName);
            defaultField.setValue(fieldValue);
            defaultField.setType(fieldType);
            defaultField.setField(field);

            // 转换后的字段信息默认设置处理
            defaultField.setMappingCondition(true);
            defaultField.setMappingName(fieldName);
            defaultField.setMappingValue(fieldValue);
            defaultField.setMappingType(fieldType);

            // 注解信息-name() 的处理
            if (field.isAnnotationPresent(BeanMapping.class)) {
                BeanMapping beanMapping = field.getAnnotation(BeanMapping.class);
                String mappingName = beanMapping.name();
                if (StringUtil.isNotEmpty(mappingName)) {
                    defaultField.setMappingName(mappingName);
                }
            }
            return defaultField;
        } catch (IllegalAccessException e) {
            throw new BeanMappingRuntimeException(e);
        }
    }

}
