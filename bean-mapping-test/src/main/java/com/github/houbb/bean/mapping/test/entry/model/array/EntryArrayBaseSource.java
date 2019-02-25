package com.github.houbb.bean.mapping.test.entry.model.array;

import com.github.houbb.bean.mapping.api.annotation.BeanMappingEntry;
import com.github.houbb.bean.mapping.api.core.IConvert;
import com.github.houbb.bean.mapping.test.annotation.convert.StringSuffixConvert;

/**
 * <p> </p>
 *
 * <pre> Created: 2019/2/25 9:03 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class EntryArrayBaseSource {

    @BeanMappingEntry
    private String[] baseStrings;

    public String[] getBaseStrings() {
        return baseStrings;
    }

    public void setBaseStrings(String[] baseStrings) {
        this.baseStrings = baseStrings;
    }

}
