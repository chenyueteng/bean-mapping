package com.github.houbb.bean.mapping.test.annotation.name;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;

/**
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class FooNameTarget {

    /**
     * 视图属性字段
     */
    private String modelVo;

    /**
     * 名称
     */
    @BeanMapping(name = "name")
    private String nameVo;

    public String getModelVo() {
        return modelVo;
    }

    public void setModelVo(String modelVo) {
        this.modelVo = modelVo;
    }

    public String getNameVo() {
        return nameVo;
    }

    public void setNameVo(String nameVo) {
        this.nameVo = nameVo;
    }
}
