package com.carme.ums.exception;

/**
 * Created by huangkl on 2018/5/2.
 */
public class UmsException extends RuntimeException {

    protected Integer code;

    protected String  message;

    public UmsException() {
        super();
    }

    public UmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UmsException(String message) {
        super(message);
    }

    public UmsException(Throwable cause) {
        super(cause);
    }

    public UmsException(Integer code, String message) {
        super(code + ":" + message);
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
