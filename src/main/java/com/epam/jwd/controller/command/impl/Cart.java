package com.epam.jwd.controller.command.impl;

import com.epam.jwd.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Cart instance;
    private Map<Product, Integer> cart;
    private double totalPrice;

    private Cart() {
        cart = new HashMap<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            return instance = new Cart();
        }
        return instance;
    }

    public void addToCart(Product products, int amount) {
        cart.put(products, amount);
        totalPrice += (products.getPricePerHour() * amount);
    }

    public void removeFromCart(Product product) {
        totalPrice -= (product.getPricePerHour() * cart.get(product));
        cart.remove(product);
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public double getTotalAmount() {
        return totalPrice;
    }

}
