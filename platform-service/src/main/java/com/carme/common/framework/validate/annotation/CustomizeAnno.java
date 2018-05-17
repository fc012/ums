package com.carme.common.framework.validate.annotation;

import java.lang.annotation.*;

import com.carme.common.framework.validate.ValidateFactory;
import com.carme.common.framework.validate.extend.AbstractExecutor;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CustomizeAnno {

    Class<? extends AbstractExecutor> setClass();

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
    String errMsg() default ValidateFactory.DEFAULT_ERROR_FLAG.CUSTOMZE_ANNO;

}
