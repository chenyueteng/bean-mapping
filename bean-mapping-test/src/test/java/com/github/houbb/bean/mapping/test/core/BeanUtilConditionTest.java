package com.github.houbb.bean.mapping.test.core;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.annotation.condition.model.FooConditionSource;
import com.github.houbb.bean.mapping.test.annotation.condition.model.FooConditionTarget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 条件测试
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class BeanUtilConditionTest {

    /**
     * 测试 source 中没有值就不会去设置的情况
     */
    @Test
    public void notNullConditionTest() {
        FooConditionSource fooConditionSource = new FooConditionSource();
        final String targetValue = "targetNotNullName";
        FooConditionTarget fooConditionTarget = new FooConditionTarget();
        fooConditionTarget.setNotNullName(targetValue);

        //1. 当 source 中没有值时
        BeanUtil.copyProperties(fooConditionSource, fooConditionTarget);
        Assertions.assertEquals(targetValue, fooConditionTarget.getNotNullName());

        //2. 当 source 中设置值时
        final String sourceValue = "sourceNotNullName";
        fooConditionSource.setNotNullName(sourceValue);
        BeanUtil.copyProperties(fooConditionSource, fooConditionTarget);
        Assertions.assertEquals(sourceValue, fooConditionTarget.getNotNullName());
    }

    /**
     * 测试 target 中有值就不会去设置的情况
     */
    @Test
    public void nullConditionTest() {
        final String sourceNullView = "sourceNullView";
        final String targetNullView = "targetNullView";

        FooConditionSource fooConditionSource = new FooConditionSource();
        fooConditionSource.setNullView(sourceNullView);

        FooConditionTarget fooConditionTarget = new FooConditionTarget();
        fooConditionTarget.setNullView(targetNullView);

        //1. 当 target 字段有值时
        BeanUtil.copyProperties(fooConditionSource, fooConditionTarget);
        Assertions.assertEquals(targetNullView, fooConditionTarget.getNullView());

        //2. 当 target 字段信息为 null 时
        fooConditionTarget.setNullView(null);
        BeanUtil.copyProperties(fooConditionSource, fooConditionTarget);
        Assertions.assertEquals(sourceNullView, fooConditionTarget.getNullView());
    }

    /**
     * idRemark，只有 id 不为空才进行设置。
     */
    @Test
    public void idExistsConditionTest() {
        final String idRemark = "idRemark";

        FooConditionSource fooConditionSource = new FooConditionSource();
        fooConditionSource.setIdRemark(idRemark);

        FooConditionTarget fooConditionTarget = new FooConditionTarget();

        //1. source 对象中的 id 没有值
        BeanUtil.copyProperties(fooConditionSource, fooConditionTarget);
        Assertions.assertNull(fooConditionTarget.getIdRemark());

        //2. source 对象中的 id 有值
        fooConditionSource.setId(1L);
        BeanUtil.copyProperties(fooConditionSource, fooConditionTarget);
        Assertions.assertEquals(idRemark, fooConditionTarget.getIdRemark());
    }

}
