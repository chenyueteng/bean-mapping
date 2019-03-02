package com.github.houbb.bean.mapping.test.entry.model.bean;

import com.github.houbb.bean.mapping.api.annotation.BeanMappingEntry;
import com.github.houbb.bean.mapping.test.entry.model.component.EntryBeanComponent;

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
     * 单个字段信息处理
     */
    @BeanMappingEntry
    private EntryBeanComponent component;

    public EntryBeanComponent getComponent() {
        return component;
    }

    public void setComponent(EntryBeanComponent component) {
        this.component = component;
    }
}
