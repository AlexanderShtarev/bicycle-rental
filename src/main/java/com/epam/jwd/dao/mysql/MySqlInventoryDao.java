package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.InventoryDao;
import com.epam.jwd.dao.constant.InventoryFieldsConstant;
import com.epam.jwd.domain.*;
import sun.net.www.content.text.Generic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlInventoryDao extends GenericDao<Inventory> implements InventoryDao {
    private static MySqlInventoryDao instance;

    private static final String SQL_GET_ALL_INVENTORIES =
            "SELECT inventory.id, inventory.product_id, inventory.store_id\n" +
                    "FROM inventory\n" +
                    "WHERE inventory.id = ?";

    private static final String SQL_ADD_INVENTORY =
            "INSERT INTO inventory (product_id, store_id)\n" +
                    "VALUES (?, ?)";

    private static final String SQL_UPDATE_INVENTORY =
            "UPDATE inventory\n" +
                    "SET product_id = ?, store_id = ?\n" +
                    "WHERE inventory.id = ?";

    private static final String SQL_DELETE_INVENTORY =
            "DELETE inventory FROM inventory\n" +
                    "WHERE inventory.id = ?";

    public static MySqlInventoryDao getInstance() {
        if (instance == null)
            instance = new MySqlInventoryDao();
        return instance;
    }

    @Override
    protected Inventory mapToEntity(ResultSet rs) throws SQLException {

        return Inventory.builder()
                .id(rs.getLong(InventoryFieldsConstant.INVENTORY_ID))
                .product(Product.builder()
                        .id(rs.getLong(InventoryFieldsConstant.INVENTORY_PRODUCT_ID))
                        .build())
                .store(Store.builder()
                        .id(rs.getLong(InventoryFieldsConstant.INVENTORY_STORE_ID))
                        .build())
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Inventory inventory) throws SQLException {

        ps.setLong(1, inventory.getProduct().getId());
        ps.setLong(2, inventory.getStore().getId());

    }

}
