package com.oleinik.util;

public class StringUtils {

    public static boolean isEnglishFirstChar(String str) {
        if (str == null || str.isEmpty()) return false;
        char c = str.charAt(0);
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

}
