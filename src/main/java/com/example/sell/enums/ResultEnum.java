package com.example.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "form contents are not correct"),
    ORDER_NOT_EXIST(2, "order does not exist"),,

    PRODUCT_NOT_EXIST(10, "does not exist"),
    PRODUCT_STOCK_ERROR(11,"stock is not enough"),
    ORDER_NOT_EXIST(12, "order does not exist"),
    ORDERDETAIL_NOT_EXIST(13, "order detail does not exist"),
    ORDER_STATUS_ERROR(14, "order status error"),
    ORDER_UPDATE_FAIL(15, "order update fail"),
    ORDER_DETAIL_EMPTY(16, "order detail empty"),
    ORDER_PAY_STATUS_ERROR(17, "pay status error"),
    CART_EMPTY(18, "cart is empty"),
    ORDER_OWNER_ERROR(19, "order does not belong to this owner id"),

    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
