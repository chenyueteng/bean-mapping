package com.github.houbb.bean.mapping.api.annotation;

import com.github.houbb.bean.mapping.api.core.ICondition;
import com.github.houbb.bean.mapping.api.core.IConvert;

/**
 * <p> BeanMappingEntry 注解 </p>
 *
 * 1. 用在集合属性字段上
 * 2. 用在对象属性字段上
 *
 * <pre> Created: 2019/2/19 10:11 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 * @since 0.1.0
 */
public @interface BeanMappingEntry {

    /**
     * 字段的名称
     * 如果不填，则默认使用字段的名称
     * @return 名称
     */
    String name() default "";

    /**
     * 生效条件
     * 1. 默认为生效
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
