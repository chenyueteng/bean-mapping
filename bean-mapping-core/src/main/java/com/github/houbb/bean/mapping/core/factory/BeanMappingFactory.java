/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.factory;

import com.github.houbb.bean.mapping.api.core.IBeanMpping;
import com.github.houbb.bean.mapping.core.api.core.DefaultBeanMapping;
import com.github.houbb.bean.mapping.core.util.ObjectUtil;

/**
 * Bean 映射工厂
 * @author binbin.hou
 * date 2019/2/19
 */
public final class BeanMappingFactory {

    /**
     * 用于保存当前线程的信息
     */
    private static final ThreadLocal<IBeanMpping> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取对应的实现
     * 1. 线程安全
     * @return 结果
     */
    public static IBeanMpping getInstance() {
        IBeanMpping beanMpping = THREAD_LOCAL.get();
        if(ObjectUtil.isNull(beanMpping)) {
            beanMpping = new DefaultBeanMapping();
            THREAD_LOCAL.set(beanMpping);
        }
        return beanMpping;
    }


    /**
     * 清空
     * 1. 建议在每个线程执行结束，调用
     */
    public static void clear() {
        THREAD_LOCAL.remove();
    }

}
