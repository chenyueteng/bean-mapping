package com.github.houbb.bean.mapping.api.core;

/**
 * <p> 转换接口 </p>
 *
 * <pre> Created: 2019/2/19 10:15 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 * @since 0.0.2
 */
public interface IConvert<R, T> {

    /**
     * 将原始信息转换为目标信息
     * @param context 当前执行上下文
     * @param resource 原始对象
     * @return 转换结果
     */
    T convert(final IContext context, R resource);

}
