package com.example.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0, "Not paid yet"),
    SUCCESS(1,"Already paid"),
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
