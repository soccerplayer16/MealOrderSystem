package com.example.sell.utils;

import java.util.Random;

public class KeyUtil {

    // time + random number
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 10; //random number
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
