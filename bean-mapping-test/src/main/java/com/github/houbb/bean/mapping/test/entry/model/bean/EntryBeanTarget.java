package com.github.houbb.bean.mapping.test.entry.model.bean;

import com.github.houbb.bean.mapping.test.entry.model.component.EntryBeanComponent;

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
     * 单个字段信息处理
     */
    private EntryBeanComponent component;

    public EntryBeanComponent getComponent() {
        return component;
    }

    public void setComponent(EntryBeanComponent component) {
        this.component = component;
    }
}
