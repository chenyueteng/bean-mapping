package com.github.houbb.bean.mapping.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanMappingEntry {
}
