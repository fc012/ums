package com.carme.common.framework.validate.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.carme.common.framework.validate.execute.AbstractValidate;

public class FieldRule {

    private String                     fieldName;

    private Class<?>                   fieldClass;

    private String                     aliasName;

    List<AbstractValidate>             fieldRuleList = new ArrayList<AbstractValidate>();

    private Integer                    fieldType;

    private HashMap<String, FieldRule> childMap;

    public HashMap<String, FieldRule> getChildMap() {
        return childMap;
    }

    public void setChildMap(HashMap<String, FieldRule> childMap) {
        this.childMap = childMap;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<AbstractValidate> getFieldRuleList() {
        return fieldRuleList;
    }

    public void setFieldRuleList(List<AbstractValidate> fieldRuleList) {
        this.fieldRuleList = fieldRuleList;
    }

    public Class<?> getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(Class<?> fieldClass) {
        this.fieldClass = fieldClass;
    }

}
