package com.carme.common.framework.validate.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AliasAnno {

    /**
     * 别名
     * 
     * @return
     */
    String name();

}
