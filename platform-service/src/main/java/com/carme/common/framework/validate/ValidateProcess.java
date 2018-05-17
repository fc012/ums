package com.carme.common.framework.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carme.common.framework.validate.annotation.*;
import com.carme.common.framework.validate.common.FieldResult;
import com.carme.common.framework.validate.common.FieldRule;
import com.carme.common.framework.validate.common.ValidateError;
import com.carme.common.framework.validate.common.ValidateResult;
import com.carme.common.framework.validate.execute.AbstractValidate;
import com.carme.common.framework.validate.execute.RequireValidate;
import com.carme.common.util.ObjectUtil;

/**
 * @Type ValidateProcess
 * @Desc 注释验证类
 * @author kelei.huang
 * @date 2012-7-3
 * @Version V1.0
 */
public class ValidateProcess {

    private static Map<String, Object>                     existsAnnoMap      = new HashMap<String, Object>();

    private static Map<String, HashMap<String, FieldRule>> includeObjectRules = new HashMap<String, HashMap<String, FieldRule>>();

    private static Map<String, Object>                     excludeObjectRules = new HashMap<String, Object>();

    public final static int                                FIELD_TYPE_ATTR    = 1;

    public final static int                                FIELD_TYPE_OBJECT  = 2;

    public final static int                                FIELD_TYPE_LIST    = 3;

    static {
        existsAnnoMap.put(AliasAnno.class.getName(), null);
        existsAnnoMap.put(CustomizeAnno.class.getName(), null);
        existsAnnoMap.put(LengthAnno.class.getName(), null);
        existsAnnoMap.put(NumberAnno.class.getName(), null);
        existsAnnoMap.put(RegexAnno.class.getName(), null);
        existsAnnoMap.put(RequireAnno.class.getName(), null);
    }

    public static boolean isAnnoType(Class<?> type) {
        if (existsAnnoMap.containsKey(type.getName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否有注释验证信息
     * 
     * @param paramType
     * @return
     */
    public static boolean hasAnnotation(Class<?> cla) {
        for (Class<?> curCla = cla; curCla != null; curCla = curCla.getSuperclass()) {
            if (curCla == Object.class) {
                break;
            }

            Field[] fields = curCla.getDeclaredFields();
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    Class<?> type = annotation.annotationType();

                    if (isAnnoType(type)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 验证对象
     * 
     * @param entity
     * @return
     * @throws Exception
     */
    public static ValidateResult validateObject(Object entity) {
        return validateObject(null, entity);
    }

    /**
     * 验证对象
     * 
     * @param entity
     * @return
     * @throws Exception
     */
    public static ValidateResult validateObject(HttpServletRequest request, Object entity) {
        ValidateResult result = new ValidateResult();
        ValidateError error = validate(request, entity);
        Map<String, String> errorsMap = error.getErrorMap();
        result.setCodeList(error.getCodeList());
        if (errorsMap.size() == 0) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setErrorMsg(composeMapStr(errorsMap));

        }
        return result;
    }

    /**
     * 通过对象注解验证数据
     * 
     * @param entity
     * @return
     * @throws Exception
     */
    public static ValidateError validate(HttpServletRequest request, Object entity) {
        ValidateError error = new ValidateError();
        Map<String, String> errorMap = new HashMap<String, String>();
        error.setErrorMap(errorMap);
        List<String> codeList = new ArrayList<String>();
        error.setCodeList(codeList);
        // 获得对象,及继承对象
        List<AbstractValidate> ruleList = initRealRule(request, entity);

        if (ruleList.size() == 0) {
            return error;
        }

        for (AbstractValidate rule : ruleList) {
            FieldResult validateField = rule.validate();
            if (!validateField.isSuccess()) {
                String fieldName = rule.getFiledName();
                // 一个属性的错误合并到一起
                if (errorMap.containsKey(fieldName)) {
                    String data = errorMap.get(fieldName);
                    errorMap.put(fieldName, data + "," + validateField.getErrorMsg());
                } else {
                    errorMap.put(fieldName, validateField.getErrorMsg());
                }
                if (StringUtils.isNotBlank(validateField.getCode())) {
                    codeList.add(validateField.getCode());
                }
            }
        }
        return error;
    }

    /**
     * 初始化具体验证规则
     * 
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    private static List<AbstractValidate> initRealRule(HttpServletRequest request, Object entity) {
        List<AbstractValidate> resultList = new ArrayList<AbstractValidate>();
        // 获得验证规则
        Map<String, FieldRule> objectRule = getObjectRule(entity);
        if (objectRule == null) {
            RequireValidate require = new RequireValidate();
            require.setFiledName("param");
            require.setErrorMsg(ValidateFactory.DEFAULT_ERROR_FLAG.REQUIRE_ANNO);
            resultList.add(require);
            return resultList;
        }
        if (entity != null) {
            JSONObject jsonMap = (JSONObject) JSON.toJSON(entity);
            // 绑定验证规则值
            bindValidateValue(resultList, objectRule, jsonMap);
        }
        return resultList;
    }

    /**
     * 绑定验证规则值
     * 
     * @param resultList
     * @param fieldRuleMap
     * @param jsonObject
     */
    private static void bindValidateValue(List<AbstractValidate> resultList,
                                          Map<String, FieldRule> fieldRuleMap, JSONObject jsonObject) {
        for (String fieldName : fieldRuleMap.keySet()) {
            FieldRule fieldRule = fieldRuleMap.get(fieldName);
            if (fieldRule.getFieldType() == FIELD_TYPE_ATTR) {
                List<AbstractValidate> fieldRuleList = fieldRule.getFieldRuleList();
                if (fieldRuleList == null) {
                    continue;
                }
                String fieldValue = jsonObject.getString(fieldName);
                fieldValue = HtmlUtils.htmlUnescape(fieldValue);
                List<AbstractValidate> resultRuleList = new ArrayList<AbstractValidate>();
                for (AbstractValidate validateRule : fieldRuleList) {
                    AbstractValidate newValidate;
                    try {
                        newValidate = (AbstractValidate) validateRule.clone();
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                    newValidate.setFiledName(fieldName);
                    if (StringUtils.isNotBlank(fieldValue)) {
                        newValidate.setValue(fieldValue);
                    } else {
                        newValidate.setValue(null);
                    }
                    resultRuleList.add(newValidate);
                }

                resultList.addAll(resultRuleList);
            } else if (fieldRule.getFieldType() == FIELD_TYPE_OBJECT) {
                HashMap<String, FieldRule> childRuleMap = fieldRule.getChildMap();
                JSONObject childObject = (JSONObject) jsonObject.get(fieldName);
                if (childObject == null) {
                    RequireValidate require = new RequireValidate();
                    //                    fieldName = HTMLBaseFontElement.
                    require.setFiledName(fieldName);
                    require.setErrorMsg(ValidateFactory.DEFAULT_ERROR_FLAG.REQUIRE_ANNO);
                    resultList.add(require);
                } else {
                    bindValidateValue(resultList, childRuleMap, childObject);
                }
            } else if (fieldRule.getFieldType() == FIELD_TYPE_LIST) {
                HashMap<String, FieldRule> childRuleMap = fieldRule.getChildMap();
                JSONArray childArray = (JSONArray) jsonObject.get(fieldName);
                if (childArray == null) {
                    RequireValidate require = new RequireValidate();
                    require.setFiledName(fieldName);
                    require.setErrorMsg(ValidateFactory.DEFAULT_ERROR_FLAG.REQUIRE_ANNO);
                    resultList.add(require);
                } else {
                    for (int i = 0; i < childArray.size(); i++) {
                        JSONObject item = childArray.getJSONObject(i);
                        bindValidateValue(resultList, childRuleMap, item);
                    }
                }
            }

        }
    }

    /**
     * 获得对象验证规则
     * 
     * @param entity
     * @param errorMap
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private static Map<String, FieldRule> getObjectRule(Object entity) {
        if (entity == null) {
            return null;
        }

        String className = entity.getClass().getName().toString();
        HashMap<String, FieldRule> findObjectRule = includeObjectRules.get(className);
        if (findObjectRule != null) {
            return (Map<String, FieldRule>) findObjectRule;
        }
        if (excludeObjectRules.containsKey(className)) {
            return null;
        }
        Class<?> entityClass = entity.getClass();
        HashMap<String, FieldRule> objectRule = recursionField(entityClass, null);
        if (objectRule.size() != 0) {
            includeObjectRules.put(className, objectRule);
            return (HashMap<String, FieldRule>) objectRule;
        } else {
            excludeObjectRules.put(className, null);
            return null;
        }
    }

    /**
     * 递归设置字段规则
     * @param entityClass
     * @param parent
     * @return
     * @throws Exception
     */
    private static HashMap<String, FieldRule> recursionField(Class<?> entityClass, FieldRule parent) {
        HashMap<String, FieldRule> childMap = new HashMap<String, FieldRule>();
        if (parent != null) {
            parent.setChildMap(childMap);
        }
        // 循环父类
        for (Class<?> cla = entityClass; cla != null; cla = cla.getSuperclass()) {
            if (cla == Object.class) {
                break;
            }
            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                if (ObjectUtil.isBaseObject(field.getType())) {
                    FieldRule rule = getFieldRule(FIELD_TYPE_ATTR, field);
                    if (rule.getFieldRuleList().size() != 0) {
                        childMap.put(field.getName(), rule);
                    }
                } else if (field.getType().isAssignableFrom(List.class)) {
                    FieldRule rule = getFieldRule(FIELD_TYPE_LIST, field);
                    if (rule.getFieldRuleList().size() != 0) {
                        childMap.put(field.getName(), rule);
                        Type fc = field.getGenericType();
                        if (fc != null && fc instanceof ParameterizedType) {
                            ParameterizedType pt = (ParameterizedType) fc;
                            Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                            recursionField(genericClazz, rule);
                        }
                    }
                } else if (!field.getType().isPrimitive() && !field.getType().isMemberClass()) {
                    FieldRule rule = getFieldRule(FIELD_TYPE_OBJECT, field);
                    if (rule.getFieldRuleList().size() != 0) {
                        childMap.put(field.getName(), rule);
                        recursionField(field.getType(), rule);
                    }
                }
            }
        }
        return childMap;
    }

    private static FieldRule getFieldRule(int fileType, Field field) {
        FieldRule fieldRule = new FieldRule();
        fieldRule.setFieldType(fileType);
        String fieldName = field.getName();
        fieldRule.setFieldName(fieldName);
        // 设置验证规则
        List<AbstractValidate> fieldRuleList = getFieldRuleList(field);
        fieldRule.setFieldRuleList(fieldRuleList);
        return fieldRule;

    }

    /**
     * 获得单个字段验证规则
     * 
     * @param field
     * @param fieldMap
     * @param errorMap
     */
    private static List<AbstractValidate> getFieldRuleList(Field field) {
        List<AbstractValidate> validateList = new ArrayList<AbstractValidate>();
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<?> type = annotation.annotationType();
            AbstractValidate validate = ValidateFactory.getValidate(field, type);
            if (validate == null) {
                continue;
            }
            validateList.add(validate);

        }
        return validateList;
    }

    private static String composeMapStr(Map<String, String> map) {
        StringBuffer result = new StringBuffer();
        for (String key : map.keySet()) {
            if (result.length() != 0) {
                result.append(",");
            }
            result.append(map.get(key));
        }
        return result.toString();
    }

}
