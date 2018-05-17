package com.carme.common.framework.validate.execute;

import org.apache.commons.lang.StringUtils;

import com.carme.common.util.RegexUtil;

public class NumberValidate extends AbstractValidate {

    @Override
    protected boolean execute() {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        String pattern = "^\\d+$";
        return RegexUtil.regexMatch(pattern, value);
    }
}
