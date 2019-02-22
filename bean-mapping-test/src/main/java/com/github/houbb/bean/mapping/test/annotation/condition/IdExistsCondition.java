package com.github.houbb.bean.mapping.test.annotation.condition;

import com.github.houbb.bean.mapping.api.core.ICondition;
import com.github.houbb.bean.mapping.api.core.IContext;
import com.github.houbb.bean.mapping.api.core.IField;

import java.util.List;

/**
 * ID 存在时，才进行设置
 * 1. 演示用在 source 中。
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class IdExistsCondition implements ICondition {

    @Override
    public boolean condition(IContext context) {
        //1. 假设其他属性中有个属性叫 id
        // 如果id有值，表示更新，我们就设置值，如果没有值我们就不设置值。
        List<IField> fieldList = context.getAllSourceFields();
        for(IField field : fieldList) {
            final String name = field.getMappingName();
            if("id".equals(name)
                && field.getMappingValue() != null) {
                return true;
            }
        }
        return false;
    }

}
