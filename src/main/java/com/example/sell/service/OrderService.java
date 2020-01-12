package com.example.sell.service;


import com.example.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /**
     * create order
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * search orders
     */
    OrderDTO findOne(String orderId);

    /**
     * search order based on openId.
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    Page<OrderDTO> findListAll(Pageable pageable);

    /**
     * cancel an order
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * finish an order
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * pay the order
     */
    OrderDTO paid(OrderDTO orderDTO);
}
