package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.ShoppingCartItemDao;
import com.epam.jwd.domain.Category;
import com.epam.jwd.domain.Manufacturer;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartItemDaoImpl implements ShoppingCartItemDao {
    private static ShoppingCartItemDaoImpl instance;

    private static final String GET_CART_ITEMS =
            "SELECT product_id, product.name, category_id, manufacturer_id, product.description,\n" +
                    "product.price, shopping_cart_item.count FROM shopping_cart_item\n" +
                    "JOIN product on product.id = shopping_cart_item.product_id\n" +
                    "WHERE cart_id = ?";

    private static final String REMOVE_ITEMS_FROM_CART = "DELETE FROM shopping_cart_item WHERE cart_id = ?";

    private static final String INSERT_ITEMS =
            "INSERT INTO shopping_cart_item (product_id, count, cart_id) VALUES (?, ?, ?)";

    public static ShoppingCartItemDao getInstance() {
        if (instance == null) {
            instance = new ShoppingCartItemDaoImpl();
        }
        return instance;
    }

    @Override
    public Map<Product, Integer> getShoppingCartItems(int cartId) throws DaoException {
        Map<Product, Integer> cartItems = new HashMap<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_CART_ITEMS)) {
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = Product.builder()
                        .id(rs.getInt("product_id"))
                        .manufacturer(Manufacturer.builder().id(rs.getInt("manufacturer_id")).build())
                        .category(Category.builder().id(rs.getInt("category_id")).build())
                        .name(rs.getString("product.name"))
                        .description(rs.getString("product.description"))
                        .price(rs.getBigDecimal("product.price"))
                        .build();
                int count = rs.getInt("count");
                cartItems.put(product, count);
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return cartItems;
    }

    @Override
    public void removeItems(Connection con, int cartId) throws DaoException {
        try (PreparedStatement ps = con.prepareStatement(REMOVE_ITEMS_FROM_CART)) {
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public void insertItems(Connection con, Map<Product, Integer> products, int cartId) throws DaoException {
        try (PreparedStatement ps = con.prepareStatement(INSERT_ITEMS)) {

            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                ps.setInt(1, entry.getKey().getId());
                ps.setInt(2, entry.getValue());
                ps.setInt(3, cartId);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

}
