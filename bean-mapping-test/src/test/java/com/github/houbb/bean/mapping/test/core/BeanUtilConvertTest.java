package com.github.houbb.bean.mapping.test.core;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.annotation.convert.model.FooConvertSource;
import com.github.houbb.bean.mapping.test.annotation.convert.model.FooConvertTarget;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 转换器测试
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class BeanUtilConvertTest {

    /**
     * 原始字段保持不变测试
     */
    @Test
    public void fieldNotChangeTest() {
        final String source = "source";
        final String target = "target";
        FooConvertSource fooConvertSource = new FooConvertSource();
        fooConvertSource.setSourceSuffix(source);

        FooConvertTarget fooConvertTarget = new FooConvertTarget();
        fooConvertTarget.setTargetSuffix(target);

        BeanUtil.copyProperties(fooConvertSource, fooConvertTarget);
        //验证原始对象不变。

        Assert.assertEquals(source, fooConvertSource.getSourceSuffix());
        Assert.assertEquals(target, fooConvertTarget.getTargetSuffix());
    }

    /**
     * 相同字段类型设置测试
     */
    @Test
    public void sameTypeTest() {
        FooConvertSource fooConvertSource = new FooConvertSource();
        fooConvertSource.setSameType("sameType");

        FooConvertTarget fooConvertTarget = new FooConvertTarget();

        //验证后缀转换器生效
        BeanUtil.copyProperties(fooConvertSource, fooConvertTarget);
        Assert.assertEquals("sameType-TEST", fooConvertTarget.getSameType());
    }

    /**
     * 将一个字段类型转换为另一种类型的测试
     */
    @Test
    public void listTypeTest() {
        FooConvertSource fooConvertSource = new FooConvertSource();
        fooConvertSource.setListStringType("listStringType");

        FooConvertTarget fooConvertTarget = new FooConvertTarget();

        //验证列表转换器生效
        BeanUtil.copyProperties(fooConvertSource, fooConvertTarget);
        Assert.assertEquals(Arrays.asList("listStringType"), fooConvertTarget.getListStringType());
    }

}
