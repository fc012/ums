package com.carme.common.framework.validate.annotation;

import java.lang.annotation.*;

import com.carme.common.framework.validate.ValidateFactory;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 正则表达式验证
 * @author kelei.huang
 *
 */
public @interface RegexAnno {
    /**
     * 正则表达式
     * @return
     */
    String pattern();

    /**
     * 错误编码
     * @return
     */
    String code() default "";

    /**
     * 错误信息
     * @return
     */
    String errMsg() default ValidateFactory.DEFAULT_ERROR_FLAG.REGEX_ANNO;
}
