/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.test.core;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.model.Address;
import com.github.houbb.bean.mapping.test.model.User;
import com.github.houbb.bean.mapping.test.vo.UserVo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

/**
 * bean 工具类测试
 * @author binbin.hou
 * date 2019/2/19
 * @since 0.0.1
 */
public class BeanUtilTest {

    @Test
    public void baseTest() {
        User user = buildUser();
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        System.out.println("转换结果: " + userVo);
    }

    /**
     * 构建用户信息
     * @return 用户
     */
    private User buildUser() {
        User user = new User();
        Address address = new Address();
        address.setCountry("中国");
        address.setStreet("上海");
        user.setAge(10);
        user.setAddress(address);
        user.setName("映射测试");
        user.setBirthday(new Date());
        user.setStringList(Arrays.asList("1", "2"));
        return user;
    }

}
