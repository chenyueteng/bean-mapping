package com.github.houbb.bean.mapping.test.core;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.annotation.name.FooNameSource;
import com.github.houbb.bean.mapping.test.annotation.name.FooNameTarget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 注解 name() 属性测试
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class BeanUtilNameTest {

    /**
     * name 属性的校验
     */
    @Test
    public void nameTest() {
        FooNameSource fooNameSource = new FooNameSource();
        fooNameSource.setModel("模型");
        fooNameSource.setName("名称");

        FooNameTarget fooNameTarget = new FooNameTarget();
        BeanUtil.copyProperties(fooNameSource, fooNameTarget);

        Assertions.assertEquals(fooNameSource.getModel(), fooNameTarget.getModelVo());
        Assertions.assertEquals(fooNameSource.getName(), fooNameTarget.getNameVo());
    }

}
