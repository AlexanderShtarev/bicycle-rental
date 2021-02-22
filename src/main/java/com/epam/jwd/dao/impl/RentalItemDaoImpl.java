package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.RentalItemDao;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.RentalItem;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalItemDaoImpl implements RentalItemDao {
    private static RentalItemDaoImpl instance = null;

    private static final String SQL_GET_RENTAL_ITEMS =
            "SELECT rental_item.id, rental_item.product_qty, rental_item.product_id, rental_item.price FROM rental_item\n" +
                    "JOIN rental r on rental_item.rental_id = r.id\n" +
                    "WHERE r.id = ? ";

    private static final String SQL_INSERT_RENTAL_ITEMS =
            "INSERT INTO rental_item(rental_id, product_id, product_qty, price) VALUES(?, ?, ?, ?)";

    public static RentalItemDaoImpl getInstance() {
        if (instance == null) {
            instance = new RentalItemDaoImpl();
        }
        return instance;
    }

    @Override
    public List<RentalItem> getRentalItems(int rentalId) throws DaoException {
        List<RentalItem> rentalItems = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SQL_GET_RENTAL_ITEMS)) {
            ps.setInt(1, rentalId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = Product.builder().id(rs.getInt("rental_item.product_id")).build();
                int qty = rs.getInt("rental_item.product_qty");
                BigDecimal price = rs.getBigDecimal("rental_item.price");
                RentalItem item = new RentalItem(product, price, qty);
                rentalItems.add(item);
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return rentalItems;
    }

    @Override
    public void insertAllByRentalId(Connection connection, List<RentalItem> items, int rentalId) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_RENTAL_ITEMS)) {
            for (RentalItem item : items) {
                ps.setInt(1, rentalId);
                ps.setInt(2, item.getProduct().getId());
                ps.setInt(3, item.getCount());
                ps.setBigDecimal(4, item.getPrice());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

}
