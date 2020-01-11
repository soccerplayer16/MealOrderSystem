package com.example.sell.dataobject;

import com.example.sell.enums.OrderStatusEnum;
import com.example.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;

    /**
     * order status, default is NEW order
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * payment status, default is WAIT to pay
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
