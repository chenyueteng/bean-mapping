package com.github.houbb.bean.mapping.test.annotation.name;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;

/**
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class FooNameSource {

    @BeanMapping(name = "modelVo")
    private String model;

    /**
     * 名称
     */
    private String name;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
