package com.github.houbb.bean.mapping.core.support.convert;

import com.github.houbb.bean.mapping.api.core.IContext;
import com.github.houbb.bean.mapping.api.core.IConvert;
import com.github.houbb.bean.mapping.core.api.core.field.DefaultField;

import java.lang.reflect.Field;

/**
 * 默认字段的转换类型
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.0.1
 */
public class DefaultFieldConvert implements IConvert<Field, DefaultField> {
    @Override
    public DefaultField convert(IContext context, Field resource) {
        return null;
    }
}
