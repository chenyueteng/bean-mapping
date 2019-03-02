package com.github.houbb.bean.mapping.test.core.entry;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.entry.model.bean.EntryBeanSource;
import com.github.houbb.bean.mapping.test.entry.model.bean.EntryBeanTarget;

import com.github.houbb.bean.mapping.test.entry.model.component.EntryBeanComponent;
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
        EntryBeanComponent component = new EntryBeanComponent();
        component.setName("component");
        EntryBeanSource source = new EntryBeanSource();
        source.setComponent(component);

        EntryBeanTarget target = new EntryBeanTarget();

        //2. 复制
        BeanUtil.copyProperties(source, target);

        //3. 结果验证
        Assertions.assertEquals("component-TEST", target.getComponent().getName());
    }

}
