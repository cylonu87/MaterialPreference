package com.anggrayudi.materialpreference.util;


/**
 * Created by Nicko on 02/11/2015.
 */
public final class TextUtil {
    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static String getStringBetween(String str, char start, char end) {
        if (start != end) {
            for (int i = str.indexOf(end) - 1; i >= 0; i--) {
                if (str.charAt(i) == start) {
                    return str.substring(i + 1, str.indexOf(end));
                }
            }
        } else {
            for (int i = str.indexOf(end) + 1; i < str.length(); i++) {
                if (str.charAt(i) == start) {
                    return str.substring(str.indexOf(start) + 1, i);
                }
            }
        }
        return "";
    }



}
