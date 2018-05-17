package com.carme.common.framework.validate.execute;

import com.carme.common.framework.validate.ValidateFactory;
import com.carme.common.framework.validate.common.FieldResult;

public abstract class AbstractValidate implements Cloneable {

    private String   filedName;

    /**
     * 传入的值
     */
    protected String value;

    /**
     * 错误编码
     */
    protected String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 错误信息
     */
    protected String errorMsg;

    /**
     * 验证业务逻辑
     * @return
     */
    protected abstract boolean execute();

    /**
     * 验证
     * @return
     */
    public FieldResult validate() {
        FieldResult result = new FieldResult();
        if (execute()) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);

            String defaultError = ValidateFactory.getDefaultError(errorMsg);
            if (defaultError != null) {
                errorMsg = filedName + ":" + defaultError;
            }
            result.setErrorMsg(errorMsg);
            result.setCode(getCode());
        }
        return result;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public Object clone() throws CloneNotSupportedException {
        //直接调用父类的clone()方法,返回克隆副本
        return super.clone();
    }
}
