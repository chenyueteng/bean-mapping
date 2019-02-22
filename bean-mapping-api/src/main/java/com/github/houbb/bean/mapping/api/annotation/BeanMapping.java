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
     * @return 具体的转换实现
     */
    Class<? extends IConvert> convert() default IConvert.class;

}
