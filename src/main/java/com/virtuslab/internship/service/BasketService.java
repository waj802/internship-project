package com.virtuslab.internship.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.stereotype.Component;

@Component
public class BasketService {

    private Basket currentBasket = new Basket();

    public Receipt getReceipt(){
        try {
//            var productDb = new ProductDb();
//            var cart = new Basket();
//            var milk = productDb.getProduct("Milk");
//            var bread = productDb.getProduct("Bread");
//            var apple = productDb.getProduct("Apple");
//            var steak = productDb.getProduct("Steak");

//            cart.addProduct(milk);
//            cart.addProduct(milk);
//            cart.addProduct(bread);
//            cart.addProduct(bread);
//            cart.addProduct(bread);
//            cart.addProduct(apple);
//            cart.addProduct(steak);

            var receiptGenerator = new ReceiptGenerator();
            var receipt = receiptGenerator.generate(currentBasket);

            return receipt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Basket addToCart(Product product) {
        try {
            this.currentBasket.getProducts().add(product);
            return this.currentBasket;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
