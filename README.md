# 项目简介

本项目用于 java 对象属性赋值。

项目中经常需要将一个对象的属性，赋值到另一个对象中。

常见的工具有很多，但都多少不够简洁，要么不够强大。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/bean-mapping/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/bean-mapping)
[![Build Status](https://www.travis-ci.org/houbb/bean-mapping.svg?branch=master)](https://www.travis-ci.org/houbb/bean-mapping?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/bean-mapping/badge.svg?branch=master)](https://coveralls.io/github/houbb/bean-mapping?branch=master)

## 特性

- 支持对象属性的浅拷贝 

# 变更日志

> [变更日志](doc/CHANGELOG.md)

# 快速开始

## 准备

JDK1.8 及其以上版本

Maven 3.X 及其以上版本

## maven 项目依赖

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>bean-mapping-core</artifactId>
    <version>${0.0.1}</version>
</dependency>
```

## 核心类说明

### BeanUtil 

提供一个简单的静态方法 copyProperties。

```java
/**
 * 复制属性
 * 将 source 中的赋值给 target 中名称相同，且可以赋值的类型中去。类似于 spring 的 BeanUtils。
 * @param source 原始对象
 * @param target 目标对象
 */
public static void copyProperties(final Object source, Object target)
```

## 示例代码

详情参见 [BeanUtilTest.java](D:\github\bean-mapping\bean-mapping-test\src\test\java\com\github\houbb\bean\mapping\test\core\BeanUtilTest.java)

```java
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
```

日志信息如下

```
转换结果: UserVo{name='映射测试', age=10, birthday=Tue Feb 19 18:02:47 CST 2019, address=Address{country='中国', street='上海'}, stringList=[1, 2]}
```