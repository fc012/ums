package com.carme.common.framework.validate.extend;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

public class LongDateValidate extends AbstractExecutor {

    @Override
    public boolean execute(String value) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            df.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
