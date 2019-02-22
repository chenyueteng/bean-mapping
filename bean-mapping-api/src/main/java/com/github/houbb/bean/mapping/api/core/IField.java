package com.github.houbb.bean.mapping.api.core;

/**
 * 映射字段信息
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public interface IField {

    /**
     * 获取字段名称
     * @return 字段名称
     */
    String getName();

    /**
     * 获取字段值
     * @return 字段值
     */
    Object getValue();

    /**
     * 当前字段的是否生效
     * @return 是否生效
     */
    boolean getMappingCondition();

    /**
     * 获取映射名称
     * @return 映射名称
     */
    String getMappingName();

    /**
     * 获取映射的实际值
     * @return 映射的实际值
     */
    Object getMappingValue();

}
