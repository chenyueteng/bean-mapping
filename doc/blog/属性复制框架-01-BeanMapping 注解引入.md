# BeanMapping 

为了更加灵活的指定注解，引入了 `@BeanMapping` 注解。

## 注解的定义

注解定义在 `bean-mapping-api` 模块中，`bean-mapping-core` 会默认引入此模块。

```java
package com.github.houbb.bean.mapping.api.annotation;

import com.github.houbb.bean.mapping.api.core.ICondition;
import com.github.houbb.bean.mapping.api.core.IConvert;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> BeanMapping 注解 </p>
 *
 * <pre> Created: 2019/2/19 10:11 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 * @since 0.1.0
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanMapping {

    /**
     * 字段的名称
     * 如果不填，则默认使用字段的名称
     * 1. 会将 source 的值赋值给 target 和当前 name 属性一致的对象。
     * @return 名称
     */
    String name() default "";

    /**
     * 生效条件
     * 1. 默认为生效
     * 2. 当放在 source 字段上时，表示是否将值赋给 target 字段
     * 当放在 target 字段上时，表示是否接受赋值。
     * 3. source+target只有同时生效时，才会发生赋值。
     * @return 具体的生效实现
     */
    Class<? extends ICondition> condition() default ICondition.class;

    /**
     * 类型转换
     * 1. 默认不进行转换
     * 2. 为了确保转换的确定性+灵活性。对象中指定这个属性，不会改变对象的属性值和类型。
     * 如果要改变原来的值，那么类型就会被限制的很多，无法足够的灵活。
     * 3. 只有当 source 的值转换后可以设置给 target，才会将 source 转换后的值赋值给 target 对应属性，其他情况不会对值产生影响。
     * @return 具体的转换实现
     */
    Class<? extends IConvert> convert() default IConvert.class;

}
```

## name 属性

有时候 source 和 target 的字段名称可能不同，只需要通过这个属性，让二者保持一致即可。

## ICondition 接口

用于指定赋值是否生效，可以实现目标对象有值就不被覆盖的常见需求。

```java
public interface ICondition {

    /**
     * 将原始信息转换为目标信息
     * @param context 当前执行上下文
     * @return 转换结果
     */
    boolean condition(final IContext context);

}
```

### IContext 上下文接口

其中 IContext 是执行的上下文，便于获取到执行的相关属性。更加灵活的指定和实现我们的功能。

##  IConvert 字段转化接口

有时候我们希望对字段的值进行处理，比如日期/金额格式化，枚举值显示的处理等等。

就可以借助这个接口，保证代码赋值的优雅性，提升代码的可复用性，更加符合 Open-Close 原则。

```java
/**
 * <p> 转换接口 </p>
 * 1. 所有的实现都应该提供默认构造器
 * <pre> Created: 2019/2/19 10:15 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @param <T> 目标泛型
 * @author houbinbin
 * @since 0.1.0
 */
public interface IConvert<T> {

    /**
     * 将原始信息转换为目标信息
     * @param context 当前执行上下文
     * @return 转换结果
     */
    T convert(final IContext context);

}
```

