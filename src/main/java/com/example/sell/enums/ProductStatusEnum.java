package com.example.sell.enums;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum ProductStatusEnum {

    UP(0, "Sale"),
    DOWN(1, "Out of stock");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
