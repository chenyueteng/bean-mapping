package com.github.houbb.bean.mapping.api.annotation;

import java.lang.annotation.*;

/**
 * <p> BeanMappingEntry 注解 </p>
 *
 * 1. 用于指定映射当前的字段进行进一步处理，比如当前字段为对象或者集合。
 * 2. 如果放在基本类型、非集合/对象属性上，则视为无效,不进行特殊处理。
 * 3. @BeanMappingEntry 单个指定时，正常执行明细的信息。
 * 4. @BeanMappingEntry 和 @BeanMapping 同时指定时：
 * (4.1) 确保 @BeanMapping name() 和 condition() 有对应的处理信息，然后进行 4.2 的处理
 * (4.2) 首先执行明细中的所有属性 @BeanMapping/@BeanMappingEntry 的处理，存储执行的结果信息。
 * (4.2) 如果 @BeanMapping 中有 convert() 信息，再根据所有的执行后的结果，在进行一次转换处理。
 *
 * 注意：
 * （w1）@BeanMappingEntry 用户在使用的时候，可能存在多层嵌套，要考虑实现的复杂程度，是否要限制。
 * （w2）@BeanMappingEntry 和 @BeanMapping 的组合情况是否过于复杂，是否要限制。
 * （w3）@BeanMappingEntry 如果放在 Map 上如何处理？默认处理 value 吗？还是说 key/value 分开处理。
 *
 * 【B1 两个注解允许同时使用的问题】
 * 1. 优点：可以提供非常灵活的实现。比如先对明细处理，然后再统一处理成我想要的样子。
 * 2. 缺点：实现逻辑会变得非常复杂。性能损失比较厉害。
 *【B2 @BeanMappingEntry 支持多层嵌套问题】
 * 1. 优点：可以实现非常强大的功能。比如列表中的对象首先处理，然后再赋值。可以实现深度拷贝。
 * 2. 缺点：实现变得非常复杂。
 * <pre> Created: 2019-2-23 11:03:35  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 * @since 0.2.0
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanMappingEntry {
}
