package com.example.sell.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductForm {

    private String productId;
    /* */
    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    // small image of a product //
    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
