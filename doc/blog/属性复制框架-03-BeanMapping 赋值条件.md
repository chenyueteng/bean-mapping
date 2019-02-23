# BeanMapping 赋值条件

有时候目标对象有值了，我们不希望被原始对象的值覆盖掉。

或者我们的赋值需要其他的条件，比如某个属性的值不为空。

这时候就需要 `condition` 登场了。

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