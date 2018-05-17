package com.carme.common.framework.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheDel {
    String key();

    String fieldKey() default "";

    /**
     * 需要参数key做缓存
     * @return
     */
    boolean needParam() default true;
}
