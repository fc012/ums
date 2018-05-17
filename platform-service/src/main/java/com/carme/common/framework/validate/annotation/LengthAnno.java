package com.carme.common.framework.validate.annotation;

import java.lang.annotation.*;

import com.carme.common.framework.validate.ValidateFactory;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 长度验证
 * @author kelei.huang
 *
 */
public @interface LengthAnno {
    /**
     * 最小长度
     * 
     * @return
     */
    int minLength() default 1;

    /**
     * 最大长度
     * 
     * @return
     */
    int maxLength();

    /**
     * 错误编码
     * @return
     */
    String code() default "";

    /**
     * 错误信息
     * 
     * @return
     */
    String errMsg() default ValidateFactory.DEFAULT_ERROR_FLAG.LENGTH_ANNO;
}
