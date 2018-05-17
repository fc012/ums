package com.carme.common.framework.validate.execute;

import org.apache.commons.lang.StringUtils;

import com.carme.common.util.RegexUtil;

public class RegexValidate extends AbstractValidate {

    /**
     * 正则表达式规则
     */
    private String pattern;

    @Override
    protected boolean execute() {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("pattern is empty");
        }
        return RegexUtil.regexMatch(pattern, value);
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
