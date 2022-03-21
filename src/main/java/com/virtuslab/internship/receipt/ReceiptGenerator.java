package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        for (var product : basket.getProducts()) {
            boolean toContinue = true;
            for (var checkingProduct : receiptEntries) {
                if (checkingProduct.product().name().equals(product.name())) {
                    toContinue = false;
                }
            }
            if (toContinue) {
                var numberOfProducts = Collections.frequency(basket.getProducts(), product);
                var totalPrice = product.price().multiply(BigDecimal.valueOf(numberOfProducts));
                if (receiptEntries.isEmpty()) {
                    receiptEntries.add(new ReceiptEntry(product, numberOfProducts, totalPrice));
                } else {
                    receiptEntries.add(new ReceiptEntry(product, numberOfProducts, totalPrice));
                }
            }
        }
        return new FifteenPercentDiscount().apply(new Receipt(receiptEntries));
    }
}
