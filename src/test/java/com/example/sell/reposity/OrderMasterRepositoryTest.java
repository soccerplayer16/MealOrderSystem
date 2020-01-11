package com.example.sell.reposity;

import com.example.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest

class OrderMasterRepositoryTest {

    private static final String OPENID = "110110";
    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("lolood");
        orderMaster.setBuyerPhone("18576649086");
        orderMaster.setBuyerAddress("wuhan hongshanqu");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {

    }

//        OrderMaster orderMaster = new OrderMaster();
//        orderMaster.setOrderId("123456");
//        orderMaster.setBuyerName("Xiao Yan");
//        orderMaster.setBuyerPhone("123456789123");
//        orderMaster.setBuyerAddress("SanJose");
//        orderMaster.setBuyerOpenid("110");
//        orderMaster.setOrderAmount(new BigDecimal(10));
//
//        OrderMaster result = repository.save(orderMaster);
//        Assert.assertNotNull(result);
//    }

}