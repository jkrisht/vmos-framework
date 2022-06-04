package com.vmos.utils;

public class DataUtil {
    synchronized public static String generateEmail(String prefix) {
        return prefix + System.currentTimeMillis() + "@test.com";
    }
}
