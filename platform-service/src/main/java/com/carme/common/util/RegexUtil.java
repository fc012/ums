package com.carme.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static boolean regexMatch(String pattern, String value) {
        if (StringUtils.isBlank(pattern) || StringUtils.isBlank(value)) {
            return false;
        }
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(value);
        return matcher.matches();
    }

}
