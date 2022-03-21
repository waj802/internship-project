package com.virtuslab.internship.controller;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.service.BasketService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value="/basket", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Receipt getReceiptInfo() {
        return basketService.getReceipt();
    }

    @RequestMapping(value="/basket", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Basket addNewBasket(@RequestBody Product product) {
        System.out.println(product.toString());
        return basketService.addToCart(product);
    }

}
