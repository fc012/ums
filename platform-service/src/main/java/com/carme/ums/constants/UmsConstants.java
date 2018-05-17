package com.carme.ums.constants;

public class UmsConstants {

    public final static String ENCODING = "utf-8";

    /**
     * 异常变量
     * @author admin
     *
     */
    public interface UMS_EXCEPTION {
        /**
         * 权限异常
         */
        int AUTH     = 1;

        /**
         * 参数异常
         */
        int PARAM    = 2;

        /**
         * 业务异常
         */
        int BUSINESS = 3;

        /**
         * 系统异常
         */
        int SYSTEM   = 99;

    }

}
