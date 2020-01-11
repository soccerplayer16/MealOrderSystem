package com.example.sell.controller;

import com.example.sell.VO.ProductInfoVO;
import com.example.sell.VO.ProductVO;
import com.example.sell.VO.ResultVO;
import com.example.sell.dataobject.ProductCategory;
import com.example.sell.dataobject.ProductInfo;
import com.example.sell.service.CategoryService;
import com.example.sell.service.ProductService;
import com.example.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
customer products
controller ==> controls what users can see in the browser
*/


@RestController
@RequestMapping("/buyer/product") //URL's prefix
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1. query all the up products 2. query category(one time done) 3. data -> json
        List<ProductInfo> productInfoList = productService.findUpAll(); // 1

        List<Integer> categoryTypeList = new ArrayList<>(); //2
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        
        /// now need to put together data ==> json type
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOSList(productInfoVOList);
            productVOList.add(productVO);
        }

        ResultVO resultVO = new ResultVO(); //json 最外层
        ProductVO productVO = new ProductVO(); //json 中间层
        ProductInfoVO productInfoVO = new ProductInfoVO(); //json下一层

        return ResultVOUtil.success(productVOList);
    }
}
