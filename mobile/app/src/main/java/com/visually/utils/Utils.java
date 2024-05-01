package com.visually.utils;

public class Utils {

    public static String shortenText(String text, int shortenToSize) {
        if (text.length() <= shortenToSize) {
            return text;
        } else {
            return text.substring(0, shortenToSize - 3) + "...";
        }
    }
}
