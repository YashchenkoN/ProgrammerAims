package com.programmer.utils;

import java.util.Random;

/**
 * Created by kolyan on 8/7/15.
 */
public class KeyGenerationUtil {
    public static String getKey() {
        String result = "";
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        result += sb.toString();
        return result;
    }
}
