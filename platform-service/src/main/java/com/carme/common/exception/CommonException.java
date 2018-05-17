package com.carme.common.exception;

/**
 * Created by huangkl on 2018/5/2.
 */
public class CommonException extends RuntimeException {

    protected Integer code;

    protected String  message;

    public CommonException(String message, Throwable cause) {
        super(cause);
        this.code = 99;
        this.message = message;
    }

    public CommonException(Integer code, String message) {
        super(code + ":" + message);
        this.code = code;
        this.message = message;
    }

    public CommonException(Integer code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
