package com.example.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"NEW"),
    FINISHED(1, "FINISHED"),
    CANCEL(2,"CANCEL"),
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

//    public static OrderStatusEnum getOrderStatusEnum(Integer code) {
//        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
//            if (orderStatusEnum.getCode().equals(code)) {
//                return orderStatusEnum;
//            }
//
//        }
//        return null;
//    }
}
