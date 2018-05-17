package com.carme.common.framework.validate.annotation;

import java.lang.annotation.*;

import com.carme.common.framework.validate.ValidateFactory;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 数字验证
 * @author kelei.huang
 *
 */
public @interface NumberAnno {
    /**
     * 错误信息
     * @return
     */
    String errMsg() default ValidateFactory.DEFAULT_ERROR_FLAG.NUMBER_ANNO;

    /**
     * 错误编码
     * @return
     */
    String code() default "";
}
