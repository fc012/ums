package com.carme.common.framework.validate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.carme.common.framework.validate.annotation.*;
import com.carme.common.framework.validate.execute.*;

public class ValidateFactory {

    public interface DEFAULT_ERROR_FLAG {

        String CUSTOMZE_ANNO = "CUSTOMZE_ANNO";

        String LENGTH_ANNO   = "LENGTH_ANNO";

        String NUMBER_ANNO   = "NUMBER_ANNO";

        String REGEX_ANNO    = "REGEX_ANNO";

        String REQUIRE_ANNO  = "REQUIRE_ANNO";
    }

    private static Map<String, String> defaultErrorMap = new HashMap<String, String>();
    static {
        defaultErrorMap.put(DEFAULT_ERROR_FLAG.CUSTOMZE_ANNO, "自定义验证失败");
        defaultErrorMap.put(DEFAULT_ERROR_FLAG.LENGTH_ANNO, "长度验证失败");
        defaultErrorMap.put(DEFAULT_ERROR_FLAG.NUMBER_ANNO, "数字验证失败");
        defaultErrorMap.put(DEFAULT_ERROR_FLAG.REGEX_ANNO, "正则表达式验证失败");
        defaultErrorMap.put(DEFAULT_ERROR_FLAG.REQUIRE_ANNO, "非空验证失败");
    }

    public static String getDefaultError(String type) {
        return defaultErrorMap.get(type);
    }

    public static AbstractValidate getValidate(Field field, Class<?> type) {
        return getValidate(field, type, null);
    }

    /**
     * 获得注释类型
     * 
     * @param field
     * @param type
     * @param value
     * @return
     */
    public static AbstractValidate getValidate(Field field, Class<?> type, String value) {
        if (type.equals(RequireAnno.class)) {
            RequireAnno requireAnno = field.getAnnotation(RequireAnno.class);
            RequireValidate requireValidate = new RequireValidate();
            requireValidate.setErrorMsg(requireAnno.errMsg());
            requireValidate.setValue(value);
            requireValidate.setCode(requireAnno.code());
            return requireValidate;
        } else if (type.equals(LengthAnno.class)) {
            LengthAnno lengthAnno = field.getAnnotation(LengthAnno.class);
            LengthValidate lengthValidate = new LengthValidate();
            lengthValidate.setErrorMsg(lengthAnno.errMsg());
            lengthValidate.setMinLength(lengthAnno.minLength());
            lengthValidate.setMaxLength(lengthAnno.maxLength());
            lengthValidate.setValue(value);
            lengthValidate.setCode(lengthAnno.code());
            return lengthValidate;
        } else if (type.equals(NumberAnno.class)) {
            NumberAnno numberAnno = field.getAnnotation(NumberAnno.class);
            NumberValidate numberValidate = new NumberValidate();
            numberValidate.setErrorMsg(numberAnno.errMsg());
            numberValidate.setValue(value);
            numberValidate.setCode(numberAnno.code());
            return numberValidate;
        } else if (type.equals(RegexAnno.class)) {
            RegexAnno regexAnno = field.getAnnotation(RegexAnno.class);
            RegexValidate regexValidate = new RegexValidate();
            regexValidate.setErrorMsg(regexAnno.errMsg());
            regexValidate.setPattern(regexAnno.pattern());
            regexValidate.setValue(value);
            regexValidate.setCode(regexAnno.code());
            return regexValidate;
        } else if (type.equals(CustomizeAnno.class)) {
            CustomizeAnno customizeAnno = field.getAnnotation(CustomizeAnno.class);
            CustomizeValidate customizeValidate = new CustomizeValidate();
            customizeValidate.setCustomizeClass(customizeAnno.setClass());
            customizeValidate.setErrorMsg(customizeAnno.errMsg());
            customizeValidate.setValue(value);
            customizeValidate.setCode(customizeAnno.code());
            return customizeValidate;

        } else {
            return null;
        }
    }
}
