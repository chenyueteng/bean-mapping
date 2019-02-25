package com.github.houbb.bean.mapping.test.entry.model.bean;

import java.util.List;

/**
 * <p> 明细-对象-目标对象 </p>
 *
 * <pre> Created: 2019/2/25 9:12 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class EntryBeanTarget {

    /**
     * 字符串转换为列表
     */
    private List<String> stringList;

    /**
     * 单个字段信息处理
     */
    private EntryBeanSourceComponent component;


    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public EntryBeanSourceComponent getComponent() {
        return component;
    }

    public void setComponent(EntryBeanSourceComponent component) {
        this.component = component;
    }
}
