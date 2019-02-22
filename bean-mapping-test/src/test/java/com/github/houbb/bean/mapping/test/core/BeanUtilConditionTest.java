package com.github.houbb.bean.mapping.test.core;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.annotation.condition.model.FooCondition;
import com.github.houbb.bean.mapping.test.annotation.condition.model.FooConditionVo;
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
        FooCondition fooCondition = new FooCondition();
        final String targetValue = "targetNotNullName";
        FooConditionVo fooConditionVo = new FooConditionVo();
        fooConditionVo.setNotNullName(targetValue);

        //1. 当 source 中没有值时
        BeanUtil.copyProperties(fooCondition, fooConditionVo);
        Assertions.assertEquals(targetValue, fooConditionVo.getNotNullName());

        //2. 当 source 中设置值时
        final String sourceValue = "sourceNotNullName";
        fooCondition.setNotNullName(sourceValue);
        BeanUtil.copyProperties(fooCondition, fooConditionVo);
        Assertions.assertEquals(sourceValue, fooConditionVo.getNotNullName());
    }

    /**
     * 测试 target 中有值就不会去设置的情况
     */
    @Test
    public void nullConditionTest() {
        final String sourceNullView = "sourceNullView";
        final String targetNullView = "targetNullView";

        FooCondition fooCondition = new FooCondition();
        fooCondition.setNullView(sourceNullView);

        FooConditionVo fooConditionVo = new FooConditionVo();
        fooConditionVo.setNullView(targetNullView);

        //1. 当 target 字段有值时
        BeanUtil.copyProperties(fooCondition, fooConditionVo);
        Assertions.assertEquals(targetNullView, fooConditionVo.getNullView());

        //2. 当 target 字段信息为 null 时
        fooConditionVo.setNullView(null);
        BeanUtil.copyProperties(fooCondition, fooConditionVo);
        Assertions.assertEquals(sourceNullView, fooConditionVo.getNullView());
    }

    /**
     * idRemark，只有 id 不为空才进行设置。
     */
    @Test
    public void idExistsConditionTest() {
        final String idRemark = "idRemark";

        FooCondition fooCondition = new FooCondition();
        fooCondition.setIdRemark(idRemark);

        FooConditionVo fooConditionVo = new FooConditionVo();

        //1. source 对象中的 id 没有值
        BeanUtil.copyProperties(fooCondition, fooConditionVo);
        Assertions.assertNull(fooConditionVo.getIdRemark());

        //2. source 对象中的 id 有值
        fooCondition.setId(1L);
        BeanUtil.copyProperties(fooCondition, fooConditionVo);
        Assertions.assertEquals(idRemark, fooConditionVo.getIdRemark());
    }

}
