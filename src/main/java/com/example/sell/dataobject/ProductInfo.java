package com.example.sell.dataobject;

import com.example.sell.enums.ProductStatusEnum;
import com.example.sell.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;
    /* */
    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    // small image of a product //
    private String productIcon;

    // 0 - normal; 1 - out of stock //
    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
