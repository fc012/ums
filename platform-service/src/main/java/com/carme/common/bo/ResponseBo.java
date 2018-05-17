package com.carme.common.bo;

import com.carme.common.constants.CommonConstants;

public class ResponseBo<T> {

    public final static String SUCCESS = CommonConstants.SUCCESS;

    public final static String ERROR   = CommonConstants.ERROR;

    private String             code    = SUCCESS;

    private String             message;

    public T                   result;

    public boolean success() {
        return SUCCESS.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
