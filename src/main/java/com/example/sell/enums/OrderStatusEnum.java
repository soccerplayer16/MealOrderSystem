package com.example.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0,"New order"),
    FINISH(1, "Finished"),
    CANCEL(2,"CANCELLED"),
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
