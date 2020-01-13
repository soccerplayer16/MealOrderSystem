package com.example.sell.service.impl;

import com.example.sell.converter.OrderMasterToOrderDTOConverter;
import com.example.sell.dataobject.OrderDetail;
import com.example.sell.dataobject.OrderMaster;
import com.example.sell.dataobject.ProductInfo;
import com.example.sell.dto.CartDTO;
import com.example.sell.dto.OrderDTO;
import com.example.sell.enums.OrderStatusEnum;
import com.example.sell.enums.PayStatusEnum;
import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.reposity.OrderDetailRepository;
import com.example.sell.reposity.OrderMasterRepository;
import com.example.sell.service.OrderService;
import com.example.sell.service.ProductService;
import com.example.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    // logic : search product quantity and price (from db)
    // calculate total price
    // write to db
    // deduct from stock

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

//    @Autowired
//    private PayService payService;
//
//    @Autowired
//    private PushMessageService pushMessageService;
//
//    @Autowired
//    private WebSocket webSocket;

    @Override
    @Transactional //事务
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            System.out.println(orderDetail.getProductId());
            System.out.println("******");
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            }
            //2. calculate total price
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //3. write order information to the db
            BeanUtils.copyProperties(productInfo, orderDetail); //Spring provides this method to copy properties
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        // 4 deduct stock
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        // 发送websocket消息
//        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }


    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderDTOPage = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderDTOPage.getContent());
        Page<OrderDTO> dtoPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderDTOPage.getTotalElements());
        return dtoPage;
    }

    @Override
    public Page<OrderDTO> findListAll(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        // 1. judge order status
        OrderMaster orderMaster = new OrderMaster();

        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单操作】 订单状态不正确 orderId={},orderStats={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 2. change order status
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster); // convert from order DTO ==> orderMaster
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null) {
            log.error("取消订单失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 3. return stock information
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productService.increaseStock(cartDTOList);

//        // 4. refund after cancellation
//        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
//            payService.refund(orderDTO);
//        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单操作】 订单状态不正确 orderId={},orderStats={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null) {
            log.error("取消完结失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //pushMessageService.orderStatus(orderDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        /// 1. judge order status
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单操作】 订单状态不正确 orderId={},orderStats={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        /// 2.check payment status
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单操作】 订单支付状态不正确 orderId={},orderStats={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 3. change payment status
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null) {
            log.error("支付订单失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
