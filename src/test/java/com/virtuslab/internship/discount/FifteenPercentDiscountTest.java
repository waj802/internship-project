package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FifteenPercentDiscountTest {

    @Test
    void shouldApply15PercentDiscountWhenItIsMoreThanThreeGrainsProductsAndPriceIsOver50() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        var cheese = productDb.getProduct("Cheese");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));
        receiptEntries.add(new ReceiptEntry(cereals, 2));
        receiptEntries.add(new ReceiptEntry(bread, 2));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price().add(cereals.price()).multiply(BigDecimal.valueOf(2));
        expectedTotalPrice = expectedTotalPrice.add(cheese.price().add(steak.price())).multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldApply15PercentDiscountWhenItIsMoreThanThreeGrainsProductsButPriceIsBelow50() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cereals, 2));
        receiptEntries.add(new ReceiptEntry(bread, 2));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price().add(cereals.price()).multiply(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApply15PercentDiscountWhenItIsLessThanThreeGrainsProductsButPriceIsOver50() {
        // Given
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = cheese.price().add(steak.price()).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApply15PercentDiscountWhenItIsLessThanThreeGrainsProductsAndPriceIsBelow50() {
        // Given
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 2));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(2));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }

}
