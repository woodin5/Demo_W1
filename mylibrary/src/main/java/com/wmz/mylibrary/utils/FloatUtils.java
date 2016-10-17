package com.wmz.mylibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/12.
 */

public class FloatUtils {

    /**
     * 判断字符串是不是float型
     */
    public static boolean isFloat(String str) {
        Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        Matcher isFloat = pattern.matcher(str);
        if (isFloat.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
