package com.github.houbb.bean.mapping.test.annotation.condition;

import com.github.houbb.bean.mapping.api.core.ICondition;
import com.github.houbb.bean.mapping.api.core.IContext;

/**
 * 当前字段不为 null 的时候条件生效。
 * 1. 一般用在 source 中，避免 null 值的设置
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class NotNullCondition implements ICondition {

    @Override
    public boolean condition(IContext context) {
        Object fieldValue = context.getCurrentSourceField().getMappingValue();
        if(fieldValue != null) {
            return true;
        }
        return false;
    }

}
