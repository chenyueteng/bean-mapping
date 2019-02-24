package com.github.houbb.bean.mapping.core.api.core.condition;

import com.github.houbb.bean.mapping.api.core.ICondition;
import com.github.houbb.bean.mapping.api.core.IContext;

/**
 * 条件永远返回为真
 * @author binbin.hou
 * @since 0.2.0
 */
public class TrueCondition implements ICondition {
    @Override
    public boolean condition(IContext context) {
        return true;
    }
}
