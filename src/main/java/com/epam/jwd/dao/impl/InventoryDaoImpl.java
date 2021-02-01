package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.InventoryDao;
import com.epam.jwd.dao.constant.*;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDaoImpl extends GenericDao<Inventory> implements InventoryDao {
    private static InventoryDao instance;

    private static final String SQL_SELECT_ALL_INVENTORIES =
            "SELECT inventory.id, inventory.product_id, inventory.store_id\n" +
                    "FROM inventory\n" +
                    "WHERE inventory.id = ?";

    private static final String SQL_CREATE_INVENTORY =
            "INSERT INTO inventory (product_id, store_id)\n" +
                    "VALUES (?, ?)";

    private static final String SQL_UPDATE_INVENTORY =
            "UPDATE inventory\n" +
                    "SET product_id = ?, store_id = ?\n" +
                    "WHERE inventory.id = ?";

    private static final String SQL_DELETE_INVENTORY =
            "DELETE inventory FROM inventory\n" +
                    "WHERE inventory.id = ?";

    public static InventoryDao getInstance() {
        if (instance == null)
            instance = new InventoryDaoImpl();
        return instance;
    }

    @Override
    protected Inventory parseResultSet(ResultSet rs) throws DaoException, SQLException {
        return Inventory.builder()
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
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, Inventory inventory) throws DaoException, SQLException {
            ps.setLong(1, inventory.getProduct().getId());
            ps.setLong(2, inventory.getStore().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, Inventory inventory) throws DaoException, SQLException {
            ps.setLong(1, inventory.getProduct().getId());
            ps.setLong(2, inventory.getStore().getId());
            ps.setLong(3, inventory.getId());
    }

}
