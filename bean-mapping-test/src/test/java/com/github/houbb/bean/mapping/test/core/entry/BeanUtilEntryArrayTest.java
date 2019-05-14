package com.github.houbb.bean.mapping.test.core.entry;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.entry.model.array.EntryArraySource;
import com.github.houbb.bean.mapping.test.entry.model.array.EntryArrayTarget;
import com.github.houbb.bean.mapping.test.entry.model.component.EntryBeanComponent;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * <p> 明细数组相关测试 </p>
 *
 * <pre> Created: 2019/2/25 9:08 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class BeanUtilEntryArrayTest {

    /**
     * 数组中存放的是基础类型
     */
    @Test
    public void baseTypeTest() {
        EntryArraySource source = new EntryArraySource();
        EntryArrayTarget target = new EntryArrayTarget();

        source.setBaseStrings(new String[]{"A", "B", "C"});
        BeanUtil.copyProperties(source, target);
        Assert.assertEquals("[A, B, C]", Arrays.toString(target.getBaseStrings()));
    }

    @Test
    public void beanTypeTest() {
        EntryBeanComponent component = new EntryBeanComponent();
        component.setName("component");
        EntryArraySource source = new EntryArraySource();
        source.setComponents(new EntryBeanComponent[]{component});

        EntryArrayTarget target = new EntryArrayTarget();
        BeanUtil.copyProperties(source, target);
        Assert.assertEquals("component-TEST", target.getComponents()[0].getName());
    }

}
