package com.github.houbb.bean.mapping.test.entry.model.array;

import com.github.houbb.bean.mapping.api.annotation.BeanMappingEntry;
import com.github.houbb.bean.mapping.test.entry.model.component.EntryBeanComponent;

/**
 * <p> 数组类 source </p>
 *
 * <pre> Created: 2019/2/25 9:03 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class EntryArraySource {

    @BeanMappingEntry
    private String[] baseStrings;

    @BeanMappingEntry
    private EntryBeanComponent[] components;

    public String[] getBaseStrings() {
        return baseStrings;
    }

    public void setBaseStrings(String[] baseStrings) {
        this.baseStrings = baseStrings;
    }

    public EntryBeanComponent[] getComponents() {
        return components;
    }

    public void setComponents(EntryBeanComponent[] components) {
        this.components = components;
    }
}
