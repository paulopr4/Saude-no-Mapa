package com.mat_brandao.saudeapp.domain.util;

import android.text.TextUtils;

/**
 * Created by Mateus Brandão on 12/09/2016.
 */
public class GenericUtil {
    public static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        str = str.toLowerCase();
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }

    public static Long getNumbersFromString(String original) {
        String numberString = original.replaceAll("\\D+","");
        return Long.valueOf(numberString);
    }

    public static Long getContentIdFromUrl(String postCode, String original) {
        String numberString = original.replaceAll("\\D+","");
        return Long.valueOf(numberString.replace(postCode, ""));
    }
}
