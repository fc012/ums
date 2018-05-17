package com.carme.common.framework.validate.execute;

import org.apache.commons.lang.StringUtils;

public class RequireValidate extends AbstractValidate {

    @Override
    protected boolean execute() {
        if (!StringUtils.isBlank(value)) {
            return true;
        } else {
            return false;
        }
    }

}
