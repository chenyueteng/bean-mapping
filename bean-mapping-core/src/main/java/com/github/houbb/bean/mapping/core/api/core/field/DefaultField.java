package com.github.houbb.bean.mapping.core.api.core.field;

import com.github.houbb.bean.mapping.api.core.IField;

import java.lang.reflect.Field;

/**
 * 默认的字段信息
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class DefaultField implements IField {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段值
     */
    private Object value;

    /**
     * 字段类型
     */
    private Class<?> type;

    /**
     * 访问修饰符
     */
    private int modifiers;

    /**
     * 原始的字段信息
     */
    private Field field;

    /**
     * 字段映射名称
     */
    private String mappingName;

    /**
     * 字段映射条件
     */
    private boolean mappingCondition;

    /**
     * 字段映射值
     */
    private Object mappingValue;

    /**
     * 映射后的字段类型
     */
    private Class<?> mappingType;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public int getModifiers() {
        return modifiers;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    @Override
    public boolean isMappingCondition() {
        return mappingCondition;
    }

    public void setMappingCondition(boolean mappingCondition) {
        this.mappingCondition = mappingCondition;
    }

    @Override
    public Object getMappingValue() {
        return mappingValue;
    }

    public void setMappingValue(Object mappingValue) {
        this.mappingValue = mappingValue;
    }

    public Class<?> getMappingType() {
        return mappingType;
    }

    public void setMappingType(Class<?> mappingType) {
        this.mappingType = mappingType;
    }
}
