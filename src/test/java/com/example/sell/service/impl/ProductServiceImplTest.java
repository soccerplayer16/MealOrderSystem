package com.example.sell.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.sell.dataobject.ProductInfo;
import com.example.sell.enums.ProductStatusEnum;
import com.example.sell.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    void findOne() throws Exception{
        ProductInfo productInfo = productService.findOne("123456");
        Assert.isTrue("123456".equals(productInfo.getProductId()));
    }

    @Test
    void findUpAll() throws Exception{
        List<ProductInfo> result = productService.findUpAll();
        Assert.isTrue(result.size() != 0);
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        //System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("Chicken");
        productInfo.setProductPrice(new BigDecimal(18));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("new product");
        productInfo.setProductIcon("xxx.com");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(4);

        ProductInfo result = productService.save(productInfo);
        Assert.isTrue(result != null);

    }
}