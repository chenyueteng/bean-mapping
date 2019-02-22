package com.github.houbb.bean.mapping.api.core;

import java.util.List;

/**
 * <p> 执行上下文接口 </p>
 * 1. 所有的实现都应该提供默认构造器
 * <pre> Created: 2019/2/19 10:15 PM  </pre>
 * <pre> Project: bean-mapping  </pre>
 *
 * @author houbinbin
 * @since 0.1.0
 */
public interface IContext {

    /**
     * 获取所有的 source 字段信息
     * @return 所有的 source 字段信息
     */
    List<IField> getAllSourceFields();

    /**
     * 获取当前 source 字段信息
     * @return 获取当前 source 字段信息
     */
    IField getCurrentSourceField();

    /**
     * 获取 source 对象
     * @return 获取 source 对象
     */
    Object getSourceObject();

    /**
     * 获取所有的 target 字段信息
     * @return 所有的 target 字段信息
     */
    List<IField> getAllTargetFields();

    /**
     * 获取当前的 target 字段信息
     * @return 当前的 target 字段信息
     */
    IField getCurrentTargetField();

    /**
     * 获取 target 对象
     * @return 获取 target 对象
     */
    Object getTargetObject();

}
