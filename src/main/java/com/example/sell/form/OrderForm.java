package com.example.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    /**
     * buyer name
     */
    @NotEmpty(message = "must fill buyer name")
    private String name;

    /**
     * buyer phone number
     */
    @NotEmpty(message = "must fill phone number")
    private String phone;

    /**
     * buyer address
     */
    @NotEmpty(message = "must fill address")
    private String address;

    /**
     * buyer's openid
     */
    @NotEmpty(message = "must fill openid")
    private String openid;

    /**
     * cart
     */
    @NotEmpty(message = "cart can not be empty")
    private String items;
}
