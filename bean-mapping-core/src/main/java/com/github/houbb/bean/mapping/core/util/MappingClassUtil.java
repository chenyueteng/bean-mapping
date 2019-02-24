package com.github.houbb.bean.mapping.core.util;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 映射的类型
 * 建议将其合并到 heaven，并且依赖最新版本的 heaven。
 * @author binbin.hou
 * date 2019/2/23
 * @since 0.2.0
 */
public class MappingClassUtil {

    /**
     * 判断一个类是JAVA类型还是用户定义类型
     * @param clz 类
     * @return 是否为 java 类
     */
    @Deprecated
    public static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }

    /**
     * 获取字段的泛型参数类型
     * [java通过反射获取List中的泛型](https://blog.csdn.net/yy19900811/article/details/24239945?utm_source=blogxgwz4)
     * @param field 字段
     * @param paramIndex 泛型参数的下标
     * @return 泛型信息
     */
    public static Class getGenericParamType(final Field field, final int paramIndex) {
        if(ObjectUtil.isNull(field)) {
            return null;
        }

        field.setAccessible(true);
        Type genericType = field.getGenericType();
        if(ObjectUtil.isNull(genericType)) {
            return null;
        }

        // 如果是泛型参数的类型
        if(genericType instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            //得到泛型里的class类型对象
            Type[] types = parameterizedType.getActualTypeArguments();
            if(ArrayUtil.isEmpty(types)) {
                return null;
            }

            return (Class<?>)types[paramIndex];
        }
        return null;
    }

    /**
     * 当前类包含指定的注解信息
     * @param clazz 类
     * @param annotationClass 注解类
     * @return 是否包含
     */
    public static boolean containsAnnotationField(final Class clazz,
                                                  final Class<? extends Annotation> annotationClass) {
        ArgUtil.notNull(clazz, "Clazz");
        ArgUtil.notNull(annotationClass, "Annotation class");

        List<Field> fieldList = ClassUtil.getAllFieldList(clazz);
        if(CollectionUtil.isEmpty(fieldList)) {
            return false;
        }

        for(Field field : fieldList) {
            if(field.isAnnotationPresent(annotationClass)) {
                return true;
            }
        }
        return false;
    }

}
