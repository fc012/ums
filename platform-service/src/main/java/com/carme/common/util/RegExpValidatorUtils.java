package com.carme.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegExpValidatorUtils {
    /**
    * 验证邮箱
    * 
    * @param 待验证的字符串
    * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isEmail(String str) {
        String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return match(regex, str);
    }

    /**
    * 验证IP地址
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isIP(String str) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        return match(regex, str);
    }

    /**
    * 验证网址Url
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isUrl(String str) {
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return match(regex, str);
    }

    /**
    * 验证电话号码
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isTelephone(String str) {
        String regex = "^(\\d{3,4}-)?\\d{6,8}$";
        return match(regex, str);
    }

    /**
    * 验证输入邮政编号
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isPostalcode(String str) {
        String regex = "^\\d{6}$";
        return match(regex, str);
    }


    /**
    * 验证输入身份证号
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isIDcard(String str) {
        String regex = "(^\\d{18}$)|(^\\d{15}$)";
        return match(regex, str);
    }

    /**
    * 验证输入两位小数
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isDecimal(String str) {
        String regex = "^[0-9]+(.[0-9]{2})?$";
        return match(regex, str);
    }

    /**
    * 验证输入一年的12个月
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isMonth(String str) {
        String regex = "^(0?[[1-9]|1[0-2])$";
        return match(regex, str);
    }

    /**
    * 验证输入一个月的31天
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isDay(String str) {
        String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
        return match(regex, str);
    }

    /**
    * 验证大写字母
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isUpChar(String str) {
        String regex = "^[A-Z]+$";
        return match(regex, str);
    }

    /**
    * 验证小写字母
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isLowChar(String str) {
        String regex = "^[a-z]+$";
        return match(regex, str);
    }

    /**
    * 验证验证输入汉字
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isChinese(String str) {
        String regex = "^[\u4e00-\u9fa5],{0,}$";
        return match(regex, str);
    }

    /**
    * 验证验证输入字符串
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isLength(String str) {
        String regex = "^.{8,}$";
        return match(regex, str);
    }

    /**
     * 
     * @return
     */
    public static boolean isHttpPrefix(String str){
        return match("^http(s)?:.*", str);
    }
    /**
    * @param regex
    * 正则表达式字符串
    * @param str
    * 要匹配的字符串
    * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
    */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}