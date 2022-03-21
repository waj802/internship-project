package com.virtuslab.internship.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    @JsonProperty("products")
    private final List<Product> products;

    public Basket() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "products=" + products +
                '}';
    }
}
