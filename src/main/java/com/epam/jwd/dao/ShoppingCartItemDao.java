package com.epam.jwd.dao;

import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.Map;

public interface ShoppingCartItemDao {

    Map<Product, Integer> getShoppingCartItems(int cartId) throws DaoException;

    void removeItems(Connection con, int cartId) throws DaoException;

    void insertItems(Connection con, Map<Product, Integer> products, int cartId) throws DaoException;

}
