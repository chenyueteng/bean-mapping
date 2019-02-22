package com.github.houbb.bean.mapping.test.annotation.condition;

import com.github.houbb.bean.mapping.api.core.ICondition;
import com.github.houbb.bean.mapping.api.core.IContext;

/**
 * 当前字段 为 null 时，条件生效。
 * 1. 一般用在 target 中，避免已经有值的属性被覆盖
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class NullCondition implements ICondition {

    @Override
    public boolean condition(IContext context) {
        Object fieldValue = context.getCurrentTargetField().getMappingValue();
        if(null == fieldValue) {
            return true;
        }
        return false;
    }

}
