package com.example.sell.controller;

import com.example.sell.VO.ResultVO;
import com.example.sell.converter.OrderFormToOrderDTOConverter;
import com.example.sell.dto.OrderDTO;
import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.form.OrderForm;
import com.example.sell.service.OrderService;
import com.example.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

//    @Autowired
//    private BuyerService buyerService;

    //Create order
    @PostMapping(value = "/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(orderForm);
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }
//
//    //订单列表
//    @GetMapping("/list")
//    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
//                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
//                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
//        if (StringUtils.isEmpty(openid)) {
//            log.error("【查询订单列表】openid为空");
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//        PageRequest request = new PageRequest(page, size);
//        Page<OrderDTO> orderDTOList = orderService.findList(openid, request);
//
//        return ResultVOUtil.success(orderDTOList.getContent());
//    }
//
//    @GetMapping("detail")
//    public ResultVO<OrderDTO> detail(@RequestParam(value = "openid") String openid,
//                                     @RequestParam("orderId") String orderId) {
//
//        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
//            log.error("【查询订单列表】openid为空");
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//
//        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
//        return ResultVOUtil.success(orderDTO);
//    }
//
//    //取消订单
//    @PostMapping("/cancel")
//    public ResultVO cancel(@RequestParam("openid") String openid,
//                           @RequestParam("orderId") String orderId) {
//        //TODO 验证订单是否为自己的
//        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
//            log.error("【查询订单列表】openid为空");
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//        buyerService.cancelOrder(openid, orderId);
//        return ResultVOUtil.success();
//    }
}
