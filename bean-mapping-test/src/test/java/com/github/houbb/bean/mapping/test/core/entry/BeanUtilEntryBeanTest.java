package com.github.houbb.bean.mapping.test.core.entry;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.entry.model.array.EntryArrayBaseSource;
import com.github.houbb.bean.mapping.test.entry.model.array.EntryArrayBaseTarget;
import com.github.houbb.bean.mapping.test.entry.model.bean.EntryBeanSource;
import com.github.houbb.bean.mapping.test.entry.model.bean.EntryBeanSourceComponent;
import com.github.houbb.bean.mapping.test.entry.model.bean.EntryBeanTarget;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p> 明细对象相关测试 </p>
 *
 * <pre> Created: 2019/2/25 9:08 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 */
public class BeanUtilEntryBeanTest {

    /**
     * 数组中存放的是基础类型
     */
    @Test
    public void beanTest() {
        //1. 原始对象
        EntryBeanSourceComponent component = new EntryBeanSourceComponent();
        component.setName("component");

        EntryBeanSourceComponent stringList = new EntryBeanSourceComponent();
        stringList.setName("stringList");

        EntryBeanSource source = new EntryBeanSource();
        source.setComponent(component);
        source.setStringList(stringList);

        EntryBeanTarget target = new EntryBeanTarget();

        //2. 复制
        BeanUtil.copyProperties(source, target);

        //3. 结果验证
        Assertions.assertEquals("component", target.getComponent().getName());
        Assertions.assertEquals("stringList", target.getStringList());
    }

}
