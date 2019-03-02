package com.github.houbb.bean.mapping.test.core.entry;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.entry.model.array.EntryArraySource;
import com.github.houbb.bean.mapping.test.entry.model.array.EntryArrayTarget;
import com.github.houbb.bean.mapping.test.entry.model.component.EntryBeanComponent;
import com.github.houbb.bean.mapping.test.entry.model.iterable.EntryIterableSource;
import com.github.houbb.bean.mapping.test.entry.model.iterable.EntryIterableTarget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * <p> 明细数组相关测试 </p>
 *
 * <pre> Created: 2019/2/25 9:08 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class BeanUtilEntryIterableTest {

    /**
     * 数组中存放的是基础类型
     */
    @Test
    public void baseTypeTest() {
        EntryIterableSource source = new EntryIterableSource();
        EntryIterableTarget target = new EntryIterableTarget();

        source.setBaseStrings(Arrays.asList("A", "B", "C"));
        BeanUtil.copyProperties(source, target);
        Assertions.assertEquals(source.getBaseStrings(), target.getBaseStrings());
    }

    @Test
    public void beanTypeTest() {
        EntryIterableSource source = new EntryIterableSource();
        EntryIterableTarget target = new EntryIterableTarget();

        EntryBeanComponent component = new EntryBeanComponent();
        component.setName("component");
        source.setComponents(Collections.singletonList(component));

        BeanUtil.copyProperties(source, target);
        Assertions.assertEquals("[EntryBeanSourceComponent{name='component-TEST'}]", target.getComponents().toString());
    }

}
