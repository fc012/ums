package com.carme.common.util;

import java.util.Date;
import java.util.List;

public class ObjectUtil {
    /**
     * 是否为Class对象
     * 
     * @param object
     * @return
     */
    public static boolean isBaseObject(Object object) {
        if (object == null) {
            return false;
        }
        return isBaseObject(object.getClass());
    }

    /**
     * 是否为Class对象
     * 
     * @param object
     * @return
     */
    public static boolean isBaseObject(Class<?> cla) {
        if (cla.equals(String.class) || cla.equals(Integer.class) || cla.equals(Long.class)
            || cla.equals(Float.class) || cla.equals(Double.class) || cla.equals(Byte.class)
            || cla.equals(Short.class) || cla.equals(Date.class)) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }

    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

}
