# BeanMapping 名称属性

有时候原始对象和目标对象的字段名称不同，实际开发中数据库层，业务层和展现层的字段会有所区别。

但是我们在处理的时候，以前设置值就要借助 BeanUtils 将名称相同的设置值，不同的手动指定，这样显然不够方便。

## name

BeanMapping 注解的 `name` 属性就是为了解决这个问题，你可以在目标对象或者原始对象的任一方指定名称(同时指定也行)，
默认不指定为字段原始名称。

# 使用案例

## 对象定义

- FooNameSource.java

```java
import com.github.houbb.bean.mapping.api.annotation.BeanMapping;

/**
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class FooNameSource {

    @BeanMapping(name = "modelVo")
    private String model;

    /**
     * 名称
     */
    private String name;

    //getter & setter
}
```

- FooNameTarget.java

```java
public class FooNameTarget {

    /**
     * 视图属性字段
     */
    private String modelVo;

    /**
     * 名称
     */
    @BeanMapping(name = "name")
    private String nameVo;
}
```

## 测试代码案例

```java
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
```