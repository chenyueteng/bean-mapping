package com.github.houbb.bean.mapping.core.api.core.context;

import com.github.houbb.bean.mapping.api.core.IContext;
import com.github.houbb.bean.mapping.api.core.IField;

import java.util.List;

/**
 * 默认的执行上下文
 * @author binbin.hou
 * date 2019/2/22
 * @since 0.1.0
 */
public class DefaultContext implements IContext {

    private List<IField> allSourceFields;

    private IField currentSourceField;

    private Object sourceObject;

    private List<IField> allTargetFields;

    private IField currentTargetField;

    private Object targetObject;

    @Override
    public List<IField> getAllSourceFields() {
        return allSourceFields;
    }

    public void setAllSourceFields(List<IField> allSourceFields) {
        this.allSourceFields = allSourceFields;
    }

    @Override
    public IField getCurrentSourceField() {
        return currentSourceField;
    }

    public void setCurrentSourceField(IField currentSourceField) {
        this.currentSourceField = currentSourceField;
    }

    @Override
    public Object getSourceObject() {
        return sourceObject;
    }

    public void setSourceObject(Object sourceObject) {
        this.sourceObject = sourceObject;
    }

    @Override
    public List<IField> getAllTargetFields() {
        return allTargetFields;
    }

    public void setAllTargetFields(List<IField> allTargetFields) {
        this.allTargetFields = allTargetFields;
    }

    @Override
    public IField getCurrentTargetField() {
        return currentTargetField;
    }

    public void setCurrentTargetField(IField currentTargetField) {
        this.currentTargetField = currentTargetField;
    }

    @Override
    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }
}
