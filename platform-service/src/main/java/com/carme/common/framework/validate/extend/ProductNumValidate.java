package com.carme.common.framework.validate.extend;

import org.apache.commons.lang.StringUtils;

import com.carme.common.util.RegexUtil;

public class ProductNumValidate extends AbstractExecutor {

    @Override
    public boolean execute(String value) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        String pattern = "^\\d+$";
        if (!RegexUtil.regexMatch(pattern, value)) {
            return false;
        }
        Long targetNum = Long.valueOf(value);
        if (targetNum == 0) {
            return false;
        }
        return true;
    }
}
