package com.github.houbb.bean.mapping.test.annotation.convert;

import com.github.houbb.bean.mapping.api.core.IContext;
import com.github.houbb.bean.mapping.api.core.IConvert;
import com.github.houbb.bean.mapping.api.core.IField;

/**
 * 字符串加上后缀
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class StringSuffixConvert implements IConvert<String> {

    /**
     * 后缀
     */
    private static final String SUFFIX = "-TEST";

    @Override
    public String convert(IContext context) {
        //1. 当前 source 字段原始的值
        final Object field = context.getCurrentSourceField().getValue();
        if(null == field) {
            return null;
        }

        return field.toString()+SUFFIX;
    }

}
