package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.RentalDao;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.builder.RentalQueryBuilder;
import com.epam.jwd.dao.constant.*;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DaoException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RentalDaoImpl extends GenericDao<Rental> implements RentalDao {
    private static RentalDao instance;
    QueryBuilder builder = RentalQueryBuilder.RENTAL_QUERY_BUILDER;

    private static final String SQL_GET_ALL_RENTALS =
            "SELECT inventory_id, product_quantity, user_id, rental_date, return_date, total, status_id\n" +
                    "FROM rental";

    private static final String SQL_ADD_RENTAL =
            "INSERT INTO rental(inventory_id, product_quantity, user_id, rental_date, return_date, total, status_id)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_RENTAL =
            "UPDATE rental\n" +
                    "SET inventory_id = ?, product_quantity = ?, user_id = ?, rental_date = ?, return_date = ?, total = ?, status_id = ?\n" +
                    "WHERE rental.id = ?;";

    private static final String SQL_DELETE_RENTAL =
            "DELETE FROM rental\n" +
                    "WHERE rental.id = ?";

    public static RentalDao getInstance() {
        if (instance == null)
            instance = new RentalDaoImpl();
        return instance;
    }

    @Override
    protected Rental parseResultSet(ResultSet rs) throws DaoException, SQLException {
        return Rental.builder()
                .id(rs.getLong(RentalFieldsConstant.RENTAL_ID))
                .productQty(rs.getInt(RentalFieldsConstant.RENTAL_PRODUCT_QTY))
                .rentalDate(rs.getDate(RentalFieldsConstant.RENTAL_RENTAL_DATE))
                .returnDate(rs.getDate(RentalFieldsConstant.RENTAL_RETURN_DATE))
                .total(rs.getDouble(RentalFieldsConstant.RENTAL_TOTAL))
                .inventory(Inventory.builder()
                        .id(rs.getLong(InventoryFieldsConstant.INVENTORY_ID))
                        .product(Product.builder()
                                .id(rs.getLong(ProductFieldsConstant.PRODUCT_ID))
                                .model(rs.getString(ProductFieldsConstant.PRODUCT_MODEL))
                                .pricePerHour(rs.getDouble(ProductFieldsConstant.PRODUCT_PRICE_PER_HOUR))
                                .producer(ProductProducer.builder()
                                        .id(rs.getLong(ProductFieldsConstant.PRODUCT_PRODUCER_ID))
                                        .name(rs.getString(ProductProducerFieldsConstant.PRODUCER_NAME))
                                        .build())
                                .type(ProductType.builder()
                                        .id(rs.getLong(ProductFieldsConstant.PRODUCT_TYPE_ID))
                                        .name(rs.getString(ProductTypeFieldsConstant.TYPE_NAME))
                                        .build())
                                .image(Image.builder()
                                        .id(rs.getLong(ProductFieldsConstant.PRODUCT_IMAGE_ID))
                                        .title(rs.getString(ImageFieldsConstant.IMAGE_TITLE))
                                        .imageLink(rs.getString(ImageFieldsConstant.IMAGE_IMAGE_LINK))
                                        .build())
                                .build())
                        .store(Store.builder()
                                .id(rs.getLong(StoreFieldsConstant.STORE_ID))
                                .address(rs.getString(StoreFieldsConstant.STORE_ADDRESS))
                                .phone(rs.getString(StoreFieldsConstant.STORE_PHONE))
                                .build())
                        .build())
                .status(RentalStatus.resolveStatusById(rs.getInt(RentalFieldsConstant.RENTAL_STATUS_ID)))
                .user(User.builder()
                        .id(rs.getLong(UserFieldsConstant.USER_ID))
                        .email(rs.getString(UserFieldsConstant.USER_EMAIL))
                        .password(rs.getString(UserFieldsConstant.USER_PASSWORD))
                        .name(rs.getString(UserFieldsConstant.USER_NAME))
                        .balance(rs.getDouble(UserFieldsConstant.USER_BALANCE))
                        .status(UserStatus.resolveStatusById(rs.getInt(UserFieldsConstant.USER_STATUS_ID)))
                        .roles(new ArrayList<>())
                        .build())
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, Rental rental) throws DaoException, SQLException {
        ps.setInt(1, rental.getProductQty());
        ps.setDate(2, new Date(rental.getRentalDate().getTime()));
        ps.setDate(3, new Date(rental.getReturnDate().getTime()));
        ps.setDouble(4, rental.getTotal());
        ps.setLong(5, rental.getInventory().getId());
        ps.setInt(6, rental.getStatus().getId());
        ps.setLong(7, rental.getUser().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, Rental rental) throws DaoException, SQLException {
        ps.setInt(1, rental.getProductQty());
        ps.setDate(2, new Date(rental.getRentalDate().getTime()));
        ps.setDate(3, new Date(rental.getReturnDate().getTime()));
        ps.setDouble(4, rental.getTotal());
        ps.setLong(5, rental.getInventory().getId());
        ps.setInt(6, rental.getStatus().getId());
        ps.setLong(7, rental.getUser().getId());
        ps.setLong(8, rental.getId());
    }


}
