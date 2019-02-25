package com.github.houbb.bean.mapping.test.entry.model.bean;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.test.annotation.convert.StringSuffixConvert;

/**
 * <p> 明细对象源数据-组件 </p>
 *
 * <pre> Created: 2019/2/25 9:13 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class EntryBeanSourceComponent {

    /**
     * 字段名称
     */
    @BeanMapping(convert = StringSuffixConvert.class)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EntryBeanSourceComponent{" +
                "name='" + name + '\'' +
                '}';
    }
}
