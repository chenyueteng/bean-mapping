package com.github.houbb.bean.mapping.test.annotation.condition.model;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.test.annotation.condition.IdExistsCondition;
import com.github.houbb.bean.mapping.test.annotation.condition.NotNullCondition;
import com.github.houbb.bean.mapping.test.annotation.condition.NullCondition;

/**
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class FooCondition {
    /**
     * 主键标识
     */
    private Long id;

    /**
     * id 相关的描述，只有 id 不为空才进行设置。
     */
    @BeanMapping(condition = IdExistsCondition.class)
    private String idRemark;

    /**
     * 只有当前值不为空的时候才进行设置
     */
    @BeanMapping(condition = NotNullCondition.class)
    private String notNullName;

    /**
     * null view
     */
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
}
