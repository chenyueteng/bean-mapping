/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.core.exception;

/**
 * 运行时异常
 * @author binbin.hou
 * date 2019/2/19
 * @since 0.0.1
 */
public class BeanMappingRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 625027404553281960L;

    public BeanMappingRuntimeException() {
    }

    public BeanMappingRuntimeException(String message) {
        super(message);
    }

    public BeanMappingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanMappingRuntimeException(Throwable cause) {
        super(cause);
    }

    public BeanMappingRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
