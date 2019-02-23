# BeanMapping 赋值条件

有时候目标对象有值了，我们不希望被原始对象的值覆盖掉。

或者我们的赋值需要其他的条件，比如某个属性的值不为空。

这时候就需要 `condition()` 登场了。

# 使用案例

## 对象定义

- FooConditionSource.java

```java
public class FooConditionSource {
    /**
     * 主键标识
     */
    private Long id;

    /**
     * id 相关的描述，只有 id 不为空才进行设置。
     */
    @BeanMapping(condition = IdExistsCondition.class)
    private String idRemark;

    /**
     * 只有当前值不为空的时候才进行设置
     */
    @BeanMapping(condition = NotNullCondition.class)
    private String notNullName;
}
```

- FooConditionTarget.java

```java
public class FooConditionTarget {

    /**
     * 主键标识
     */
    private Long id;

    /**
     * id 相关的描述，只有 id 不为空才进行设置。
     */
    private String idRemark;

    /**
     * 只有当前值为 null 的时候才允许设置值。
     */
    private String notNullName;

    /**
     * 当这个字段为 null 的时候才允许被设置值
     */
    @BeanMapping(condition = NullCondition.class)
    private String nullView;
}
```

## Condition 接口的实现

为了方便，只选取最复杂的一个讲解。

- IdExistsCondition.java 

```java
/**
 * ID 存在时，才进行设置
 * 1. 演示用在 source 中。
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class IdExistsCondition implements ICondition {

    @Override
    public boolean condition(IContext context) {
        //1. 假设其他属性中有个属性叫 id
        // 如果id有值，表示更新，我们就设置值，如果没有值我们就不设置值。
        List<IField> fieldList = context.getAllSourceFields();
        for(IField field : fieldList) {
            final String name = field.getMappingName();
            if("id".equals(name)
                && field.getMappingValue() != null) {
                return true;
            }
        }
        return false;
    }
}
```

这个主要作用在如下的属性上：

```java
/**
 * id 相关的描述，只有 id 不为空才进行设置。
 */
@BeanMapping(condition = IdExistsCondition.class)
private String idRemark;
```

## 测试代码案例

```java
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
```