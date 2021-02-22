package com.epam.jwd.service;

import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ShoppingCart;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;

import java.math.BigDecimal;

public interface CartService {

    public void addProduct(ShoppingCart cart, Product product) throws ServiceException;

    public void removeProducts(ShoppingCart cart, Product product, int number) throws ServiceException;

    public int getSize(ShoppingCart cart);

    public int getCount(ShoppingCart cart, Product product);

    public BigDecimal getTotalPrice(ShoppingCart cart);

    public void clearCart(ShoppingCart cart) throws ServiceException;

    BigDecimal calculateCost(int itemQuantity, BigDecimal itemCost);
}
