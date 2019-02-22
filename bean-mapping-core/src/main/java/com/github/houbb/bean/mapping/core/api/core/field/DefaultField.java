package com.github.houbb.bean.mapping.core.api.core.field;

import com.github.houbb.bean.mapping.api.core.IField;

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

    @Override
    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    @Override
    public boolean getMappingCondition() {
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
}
