package com.github.houbb.bean.mapping.test.core;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.annotation.name.FooName;
import com.github.houbb.bean.mapping.test.annotation.name.FooNameVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 注解 name() 属性测试
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class BeanUtilAtNameTest {

    /**
     * name 属性的校验
     */
    @Test
    public void nameTest() {
        FooName fooName = new FooName();
        fooName.setModel("模型");
        fooName.setName("名称");

        FooNameVo fooNameVo = new FooNameVo();
        BeanUtil.copyProperties(fooName, fooNameVo);

        Assertions.assertEquals(fooName.getModel(), fooNameVo.getModelVo());
        Assertions.assertEquals(fooName.getName(), fooNameVo.getNameVo());
    }

}
