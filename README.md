# 项目简介

本项目用于 java 对象属性赋值。

项目中经常需要将一个对象的属性，赋值到另一个对象中。

常见的工具有很多，但都多少不够简洁，要么不够强大。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/bean-mapping/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/bean-mapping)
[![Build Status](https://www.travis-ci.org/houbb/bean-mapping.svg?branch=master)](https://www.travis-ci.org/houbb/bean-mapping?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/bean-mapping/badge.svg?branch=master)](https://coveralls.io/github/houbb/bean-mapping?branch=master)

## 特性

- 支持对象属性的浅拷贝 

- 支持不同名称字段的指定赋值

- 支持自定义字段属性赋值的条件，比如目标字段不为 null 才进行赋值

- 支持自定义字段值转换，可以转换为其他类型，或者相同类型

### 0.2.0 版本新特性

- 支持属性字段为【对象】【集合】【数组】的赋值，对象赋值更加方便。

# 变更日志

> [变更日志](https://github.com/houbb/bean-mapping/blob/master/doc/CHANGELOG.md)

# 快速开始

## 准备

JDK1.8 及其以上版本

Maven 3.X 及其以上版本

## maven 项目依赖

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>bean-mapping-core</artifactId>
    <version>0.2.0</version>
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

# 测试代码参考

详情参见 bean-mapping-test 模块下的测试代码。

# 示例代码

## 对象的定义

- BaseSource.java & BaseTarget.java

其中 BaseSource 对象和 BaseTarget 对象的属性是相同的。

```java
public class BaseSource {

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 字符串列表
     */
    private List<String> stringList;
    
    //getter & setter
}
```

## 属性赋值测试案例

我们构建 BaseSource 的属性，然后调用

```java
BeanUtil.copyProperties(baseSource, baseTarget);
```

类似于 spring BeanUtils 和 Apache BeanUtils，并验证结果符合我们的预期。

```java
    /**
     * 基础测试
     */
    @Test
    public void baseTest() {
        BaseSource baseSource = buildBaseSource();
        BaseTarget baseTarget = new BaseTarget();
        BeanUtil.copyProperties(baseSource, baseTarget);

        // 断言赋值后的属性和原来相同
        Assertions.assertEquals(baseSource.getAge(), baseTarget.getAge());
        Assertions.assertEquals(baseSource.getName(), baseTarget.getName());
        Assertions.assertEquals(baseSource.getBirthday(), baseTarget.getBirthday());
        Assertions.assertEquals(baseSource.getStringList(), baseTarget.getStringList());
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
```

# 拓展阅读

实际工作中，我们遇到的情况会比这个复杂一些。

比如两个字段名称不同，我们也想进行赋值，值得处理转换等等。

Bean-Mapping 相关文档:

[项目模块简介和预期功能](doc/blog/属性复制框架-00-项目模块和预期功能.md)

[BeanMapping 注解的引入](doc/blog/属性复制框架-01-BeanMapping%20注解引入.md)

[不同名称字段的指定赋值](doc/blog/属性复制框架-02-BeanMapping%20名称属性.md)

[自定义赋值生效的条件](doc/blog/属性复制框架-03-BeanMapping%20赋值条件.md)

[自定义字段转换实现](doc/blog/属性复制框架-04-BeanMapping%20字段转换.md)

[支持属性字段为【对象】【集合】【数组】的赋值](doc/blog/属性复制框架-05-BeanMappingEntry.md)