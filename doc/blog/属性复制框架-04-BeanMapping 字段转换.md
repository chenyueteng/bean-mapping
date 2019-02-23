# BeanMapping 字段转换

我们在赋值的时候，有时候希望对原始对象的值做一些转换。

比如格式化，或者是从一种对象类型到另一种比较复杂的情况。

甚至需要去查询数据库，填充信息等等。

BeanMapping 中的 `convert()` 为你提供了实现的可能性。

# 使用案例

## 对象定义

- FooConvertSource.java

```java
public class FooConvertSource {

    /**
     * source 后缀
     */
    @BeanMapping(convert = StringSuffixConvert.class)
    private String sourceSuffix;

    @BeanMapping(convert = StringSuffixConvert.class)
    private String sameType;

    /**
     * 字符串转换为列表
     */
    @BeanMapping(convert = ListTypeConvert.class)
    private String listStringType;
    
    //getter & setter
}
```

- FooConvertTarget.java

```java
public class FooConvertTarget {

    /**
     * target 后缀
     */
    @BeanMapping(convert = StringSuffixConvert.class)
    private String targetSuffix;

    private String sameType;

    private List<String> listStringType;
}
```

## Convert 接口的实现

### 相同字段类型的转化

```java
/**
 * 字符串加上后缀
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class StringSuffixConvert implements IConvert<String> {

    /**
     * 后缀
     */
    private static final String SUFFIX = "-TEST";

    @Override
    public String convert(IContext context) {
        //1. 当前 source 字段原始的值
        final Object field = context.getCurrentSourceField().getValue();
        if(null == field) {
            return null;
        }

        return field.toString()+SUFFIX;
    }

}
```

### 不同字段类型的转化

```java
/**
 * 字符串列表转换
 * 1. 属于将 String 类型转换为 List 类型
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class ListTypeConvert implements IConvert<List<String>> {

    @Override
    public List<String> convert(IContext context) {
        //1. 当前 source 字段原始的值
        final Object field = context.getCurrentSourceField().getValue();
        if(null == field) {
            return null;
        }

        return Arrays.asList(field.toString());
    }

}
```

## 测试代码案例

```java
import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.annotation.convert.model.FooConvertSource;
import com.github.houbb.bean.mapping.test.annotation.convert.model.FooConvertTarget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 转换器测试
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class BeanUtilConvertTest {

    /**
     * 原始字段保持不变测试
     */
    @Test
    public void fieldNotChangeTest() {
        final String source = "source";
        final String target = "target";
        FooConvertSource fooConvertSource = new FooConvertSource();
        fooConvertSource.setSourceSuffix(source);

        FooConvertTarget fooConvertTarget = new FooConvertTarget();
        fooConvertTarget.setTargetSuffix(target);

        BeanUtil.copyProperties(fooConvertSource, fooConvertTarget);
        //验证原始对象不变。

        Assertions.assertEquals(source, fooConvertSource.getSourceSuffix());
        Assertions.assertEquals(target, fooConvertTarget.getTargetSuffix());
    }

    /**
     * 相同字段类型设置测试
     */
    @Test
    public void sameTypeTest() {
        FooConvertSource fooConvertSource = new FooConvertSource();
        fooConvertSource.setSameType("sameType");

        FooConvertTarget fooConvertTarget = new FooConvertTarget();

        //验证后缀转换器生效
        BeanUtil.copyProperties(fooConvertSource, fooConvertTarget);
        Assertions.assertEquals("sameType-TEST", fooConvertTarget.getSameType());
    }

    /**
     * 将一个字段类型转换为另一种类型的测试
     */
    @Test
    public void listTypeTest() {
        FooConvertSource fooConvertSource = new FooConvertSource();
        fooConvertSource.setListStringType("listStringType");

        FooConvertTarget fooConvertTarget = new FooConvertTarget();

        //验证列表转换器生效
        BeanUtil.copyProperties(fooConvertSource, fooConvertTarget);
        Assertions.assertEquals(Arrays.asList("listStringType"), fooConvertTarget.getListStringType());
    }
}
```