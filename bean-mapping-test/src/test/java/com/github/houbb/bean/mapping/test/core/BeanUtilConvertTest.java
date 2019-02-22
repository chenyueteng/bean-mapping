package com.github.houbb.bean.mapping.test.core;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.annotation.convert.model.FooConvertSource;
import com.github.houbb.bean.mapping.test.annotation.convert.model.FooConvertTarget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        Assertions.assertEquals(source, fooConvertSource.getSourceSuffix());
        Assertions.assertEquals(target, fooConvertTarget.getTargetSuffix());
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
        Assertions.assertEquals("sameType-TEST", fooConvertTarget.getSameType());
    }

}
