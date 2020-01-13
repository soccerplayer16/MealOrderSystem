package com.example.sell.utils;

import com.example.sell.enums.CodeEnum;


public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            //System.out.println(each);
            if (code.equals(each.getCode())) {
                //System.out.println(each);
                return each;
            }
        }
        return null;
    }
}
