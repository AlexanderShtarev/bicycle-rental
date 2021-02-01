package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.StoreDao;
import com.epam.jwd.dao.constant.StoreFieldsConstant;
import com.epam.jwd.domain.Store;
import com.epam.jwd.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StoreDaoImpl extends GenericDao<Store> implements StoreDao {

    private static StoreDao instance;

    private static final String SQL_SELECT_ALL_STORES =
            "SELECT id, address, phone\n" +
                    "FROM store";

    private static final String SQL_CREATE_STORE =
            "INSERT INTO store (address, phone)\n" +
                    "VALUES (?, ?);";

    private static final String SQL_UPDATE_STORE =
            "UPDATE store\n" +
                    "SET address = ?, phone = ?\n" +
                    "WHERE store.id = ?;";

    private static final String SQL_DELETE_STORE =
            "DELETE store FROM store\n" +
                    "WHERE store.id = ?";

    public static StoreDao getInstance() {
        if (instance == null)
            instance = new StoreDaoImpl();
        return instance;
    }

    @Override
    protected Store parseResultSet(ResultSet rs) throws DaoException, SQLException {
        return Store.builder()
                .id(rs.getLong(StoreFieldsConstant.STORE_ID))
                .address(rs.getString(StoreFieldsConstant.STORE_ADDRESS))
                .phone(rs.getString(StoreFieldsConstant.STORE_PHONE))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, Store store) throws DaoException, SQLException {
            ps.setString(1, store.getAddress());
            ps.setString(2, store.getPhone());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, Store store) throws DaoException, SQLException {
            ps.setString(1, store.getAddress());
            ps.setString(2, store.getPhone());
            ps.setLong(3, store.getId());
    }

}
