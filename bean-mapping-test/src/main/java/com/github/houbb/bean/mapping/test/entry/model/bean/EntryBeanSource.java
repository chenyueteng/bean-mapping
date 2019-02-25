package com.github.houbb.bean.mapping.test.entry.model.bean;

import com.github.houbb.bean.mapping.api.annotation.BeanMapping;
import com.github.houbb.bean.mapping.api.annotation.BeanMappingEntry;
import com.github.houbb.bean.mapping.test.annotation.convert.ListTypeConvert;

/**
 * <p> 明细-对象-源数据 </p>
 *
 * <pre> Created: 2019/2/25 9:12 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class EntryBeanSource {

    /**
     * 字符串转换为列表
     */
    @BeanMapping(convert = ListTypeConvert.class)
    @BeanMappingEntry
    private EntryBeanSourceComponent stringList;

    /**
     * 单个字段信息处理
     */
    @BeanMappingEntry
    private EntryBeanSourceComponent component;

    public EntryBeanSourceComponent getStringList() {
        return stringList;
    }

    public void setStringList(EntryBeanSourceComponent stringList) {
        this.stringList = stringList;
    }

    public EntryBeanSourceComponent getComponent() {
        return component;
    }

    public void setComponent(EntryBeanSourceComponent component) {
        this.component = component;
    }
}
