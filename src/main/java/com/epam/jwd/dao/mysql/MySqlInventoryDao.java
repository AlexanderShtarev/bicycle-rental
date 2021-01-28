package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.AbstractJDBCDao;
import com.epam.jwd.dao.InventoryDao;
import com.epam.jwd.dao.constant.InventoryFieldsConstant;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlInventoryDao extends AbstractJDBCDao<Inventory, Long> implements InventoryDao {
    private static MySqlInventoryDao instance;

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

    public static MySqlInventoryDao getInstance() {
        if (instance == null)
            instance = new MySqlInventoryDao();
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SQL_SELECT_ALL_INVENTORIES;
    }

    @Override
    public String getCreateQuery() {
        return SQL_CREATE_INVENTORY;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_INVENTORY;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_INVENTORY;
    }

    @Override
    protected List<Inventory> parseResultSet(ResultSet rs) throws DaoException {
        List<Inventory> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Inventory inventory = Inventory.builder()
                        .id(rs.getLong(InventoryFieldsConstant.INVENTORY_ID))
                        .product(Product.builder()
                                .id(rs.getLong(InventoryFieldsConstant.INVENTORY_PRODUCT_ID))
                                .build())
                        .store(Store.builder()
                                .id(rs.getLong(InventoryFieldsConstant.INVENTORY_STORE_ID))
                                .build())
                        .build();
                list.add(inventory);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, Inventory inventory) throws DaoException {
        try {
            ps.setLong(1, inventory.getProduct().getId());
            ps.setLong(2, inventory.getStore().getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, Inventory inventory) throws DaoException {
        try {
            ps.setLong(1, inventory.getProduct().getId());
            ps.setLong(2, inventory.getStore().getId());
            ps.setLong(3, inventory.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
