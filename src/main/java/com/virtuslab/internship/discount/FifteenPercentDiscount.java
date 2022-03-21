package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public class FifteenPercentDiscount {

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            var tenPercentDiscount = new TenPercentDiscount();
            var newReceipt = tenPercentDiscount.apply(receipt);
            if (newReceipt.totalPrice().compareTo(receipt.totalPrice()) != 0)
                totalPrice = totalPrice.multiply(BigDecimal.valueOf(0.9));

            return new Receipt(receipt.entries(), discounts, totalPrice);
        } else {
            var totalPrice = receipt.totalPrice();
            var discounts = receipt.discounts();
            var tenPercentDiscount = new TenPercentDiscount();
            var newReceipt = tenPercentDiscount.apply(receipt);
            if (newReceipt.totalPrice().compareTo(receipt.totalPrice()) != 0)
                totalPrice = totalPrice.multiply(BigDecimal.valueOf(0.9));

            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
    }

    private boolean shouldApply(Receipt receipt) {
        var counter = 0;
        for (var product : receipt.entries())
            if (product.product().type().equals(Product.Type.GRAINS)) {
                counter += product.quantity();
                if (counter >= 3)
                    return true;
            }
        return false;
    }

}
