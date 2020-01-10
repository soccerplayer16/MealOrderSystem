package com.example.sell.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
customer products
*/


@RestController
@RequestMapping("/buyer/product") //URL's prefix
public class BuyerProductController {

    @GetMapping("/list")
    public void list() {


    }
}
