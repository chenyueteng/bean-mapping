package com.github.houbb.bean.mapping.test.core;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.base.model.BaseSource;
import com.github.houbb.bean.mapping.test.base.model.BaseTarget;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

/**
 * 基础测试
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.0.1
 */
public class BeanUtilBaseTest {

    /**
     * 基础测试
     */
    @Test
    public void baseTest() {
        BaseSource baseSource = buildBaseSource();
        BaseTarget baseTarget = new BaseTarget();
        BeanUtil.copyProperties(baseSource, baseTarget);

        // 断言赋值后的属性和原来相同
        Assert.assertEquals(baseSource.getAge(), baseTarget.getAge());
        Assert.assertEquals(baseSource.getName(), baseTarget.getName());
        Assert.assertEquals(baseSource.getBirthday(), baseTarget.getBirthday());
        Assert.assertEquals(baseSource.getStringList(), baseTarget.getStringList());
    }

    /**
     * 构建用户信息
     * @return 用户
     */
    private BaseSource buildBaseSource() {
        BaseSource baseSource = new BaseSource();
        baseSource.setAge(10);
        baseSource.setName("映射测试");
        baseSource.setBirthday(new Date());
        baseSource.setStringList(Arrays.asList("1", "2"));
        return baseSource;
    }

}
