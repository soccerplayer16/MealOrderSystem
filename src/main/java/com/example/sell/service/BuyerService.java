package com.example.sell.service;

import com.example.sell.dto.OrderDTO;

public interface BuyerService {

    //query one order
    OrderDTO findOrderOne(String openid, String orderId);

    //cancel an order
    OrderDTO cancelOrder(String openid, String orderId);
}