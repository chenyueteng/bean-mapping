package com.github.houbb.bean.mapping.test.annotation.condition.model;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.test.annotation.condition.NullCondition;

/**
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class FooConditionTarget {

    /**
     * 主键标识
     */
    private Long id;

    /**
     * id 相关的描述，只有 id 不为空才进行设置。
     */
    private String idRemark;

    /**
     * 只有当前值为 null 的时候才允许设置值。
     */
    private String notNullName;

    /**
     * 当这个字段为 null 的时候才允许被设置值
     */
    @BeanMapping(condition = NullCondition.class)
    private String nullView;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdRemark() {
        return idRemark;
    }

    public void setIdRemark(String idRemark) {
        this.idRemark = idRemark;
    }

    public String getNotNullName() {
        return notNullName;
    }

    public void setNotNullName(String notNullName) {
        this.notNullName = notNullName;
    }

    public String getNullView() {
        return nullView;
    }

    public void setNullView(String nullView) {
        this.nullView = nullView;
    }

    @Override
    public String toString() {
        return "FooConditionTarget{" +
                "id=" + id +
                ", idRemark='" + idRemark + '\'' +
                ", notNullName='" + notNullName + '\'' +
                ", nullView='" + nullView + '\'' +
                '}';
    }
}
