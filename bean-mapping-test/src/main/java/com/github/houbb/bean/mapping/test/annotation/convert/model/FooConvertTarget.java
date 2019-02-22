package com.github.houbb.bean.mapping.test.annotation.convert.model;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.test.annotation.convert.StringSuffixConvert;

import java.util.List;

/**
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class FooConvertTarget {

    /**
     * target 后缀
     */
    @BeanMapping(convert = StringSuffixConvert.class)
    private String targetSuffix;

    private String sameType;

    private List<String> listStringType;

    public String getTargetSuffix() {
        return targetSuffix;
    }

    public void setTargetSuffix(String targetSuffix) {
        this.targetSuffix = targetSuffix;
    }

    public String getSameType() {
        return sameType;
    }

    public void setSameType(String sameType) {
        this.sameType = sameType;
    }
}
