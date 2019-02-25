package com.github.houbb.bean.mapping.test.core.entry;

import com.github.houbb.bean.mapping.core.util.BeanUtil;
import com.github.houbb.bean.mapping.test.entry.model.array.EntryArrayBaseSource;
import com.github.houbb.bean.mapping.test.entry.model.array.EntryArrayBaseTarget;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        EntryArrayBaseSource source = new EntryArrayBaseSource();
        EntryArrayBaseTarget target = new EntryArrayBaseTarget();

        source.setBaseStrings(new String[]{"A", "B", "C"});
        BeanUtil.copyProperties(source, target);
        Assertions.assertEquals(source.getBaseStrings(), target.getBaseStrings());
    }

}
