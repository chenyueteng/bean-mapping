package com.github.houbb.bean.mapping.test.annotation.convert.model;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.test.annotation.convert.StringSuffixConvert;

/**
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class FooConvertSource {

    /**
     * source 后缀
     */
    @BeanMapping(convert = StringSuffixConvert.class)
    private String sourceSuffix;

    @BeanMapping(convert = StringSuffixConvert.class)
    private String sameType;

    public String getSourceSuffix() {
        return sourceSuffix;
    }

    public void setSourceSuffix(String sourceSuffix) {
        this.sourceSuffix = sourceSuffix;
    }

    public String getSameType() {
        return sameType;
    }

    public void setSameType(String sameType) {
        this.sameType = sameType;
    }
}
