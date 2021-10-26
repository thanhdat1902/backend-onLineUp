package com.server.onlineup.common.utils;

public class NumberUtils {

    public static String generateRandomString(int length) {
        String res = "";
        while (length-- > 0) {
            res += (int) Math.floor(Math.random() * 10);
        }
        return res;
    }

}
