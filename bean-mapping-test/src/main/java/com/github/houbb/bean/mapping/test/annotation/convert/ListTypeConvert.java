package com.github.houbb.bean.mapping.test.annotation.convert;

import com.github.houbb.bean.mapping.api.core.IContext;
import com.github.houbb.bean.mapping.api.core.IConvert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 字符串列表转换
 * 1. 属于将 String 类型转换为 List 类型
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class ListTypeConvert implements IConvert<List<String>> {

    @Override
    public List<String> convert(IContext context) {
        //1. 当前 source 字段原始的值
        final Object field = context.getCurrentSourceField().getValue();
        if(null == field) {
            return null;
        }

        return Arrays.asList(field.toString());
    }

}
