package com.epam.jwd.dao;

import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ShoppingCart;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.Map;

public interface ShoppingCartDao {

    void createCart(Connection con, int userId) throws DaoException;

    ShoppingCart getCartByUserId(int userId) throws DaoException;

}
