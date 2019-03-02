package com.github.houbb.bean.mapping.test.entry.model.iterable;

import com.github.houbb.bean.mapping.api.annotation.BeanMappingEntry;
import com.github.houbb.bean.mapping.test.entry.model.component.EntryBeanComponent;

/**
 * <p> Iterable source </p>
 *
 * <pre> Created: 2019/2/25 9:03 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class EntryIterableSource {

    @BeanMappingEntry
    private Iterable<String> baseStrings;

    @BeanMappingEntry
    private Iterable<EntryBeanComponent> components;

    public Iterable<String> getBaseStrings() {
        return baseStrings;
    }

    public void setBaseStrings(Iterable<String> baseStrings) {
        this.baseStrings = baseStrings;
    }

    public Iterable<EntryBeanComponent> getComponents() {
        return components;
    }

    public void setComponents(Iterable<EntryBeanComponent> components) {
        this.components = components;
    }
}
