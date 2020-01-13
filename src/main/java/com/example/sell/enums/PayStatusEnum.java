package com.example.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0, "Not paid yet"),
    SUCCESS(1,"Already paid"),
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

//    @Override
//    public Integer getEnum() {
//        return this.getEnum();
//    }


//    public static PayStatusEnum getPayStatusEnum(Integer code) {
//        for (PayStatusEnum orderStatusEnum : PayStatusEnum.values()) {
//            if (PayStatusEnum.getCode().equals(code)) {
//                return PayStatusEnum;
//            }
//        }
//        return null;
//    }
}
