package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.ShoppingCartDao;
import com.epam.jwd.domain.ShoppingCart;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static ShoppingCartDaoImpl instance;

    private static final String GET_BY_USER_ID = "SELECT id FROM shopping_cart WHERE user_id = ?";

    public static final String CREATE_CART = "INSERT INTO shopping_cart (user_id) VALUES (?)";

    public static ShoppingCartDao getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoImpl();
        }
        return instance;
    }


    @Override
    public void createCart(Connection con, int userId) throws DaoException {
        try (PreparedStatement ps = con.prepareStatement(CREATE_CART)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public ShoppingCart getCartByUserId(int userId) throws DaoException {
        ShoppingCart cart = new ShoppingCart();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_BY_USER_ID)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cart.setId(rs.getInt("id"));
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return cart;
    }

}
