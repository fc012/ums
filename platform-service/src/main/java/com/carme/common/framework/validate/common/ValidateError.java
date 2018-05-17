package com.carme.common.framework.validate.common;

import java.util.List;
import java.util.Map;

public class ValidateError {
    private List<String>        codeList;

    private Map<String, String> errorMap;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

}
