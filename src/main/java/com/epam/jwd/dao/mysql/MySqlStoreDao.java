package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.AbstractJDBCDao;
import com.epam.jwd.dao.StoreDao;
import com.epam.jwd.dao.constant.StoreFieldsConstant;
import com.epam.jwd.domain.Store;
import com.epam.jwd.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MySqlStoreDao extends AbstractJDBCDao<Store, Long> implements StoreDao {
    private static MySqlStoreDao instance;

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

    public static MySqlStoreDao getInstance() {
        if (instance == null)
            instance = new MySqlStoreDao();
        return instance;
    }


    @Override
    public String getSelectQuery() {
        return SQL_SELECT_ALL_STORES;
    }

    @Override
    public String getCreateQuery() {
        return SQL_CREATE_STORE;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_STORE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_STORE;
    }

    @Override
    protected String getCountQuery() {
        return null;
    }

    @Override
    protected List<Store> parseResultSet(ResultSet rs) throws DaoException {
        List<Store> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Store store = Store.builder()
                        .id(rs.getLong(StoreFieldsConstant.STORE_ID))
                        .address(rs.getString(StoreFieldsConstant.STORE_ADDRESS))
                        .phone(rs.getString(StoreFieldsConstant.STORE_PHONE))
                        .build();
                list.add(store);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, Store store) throws DaoException {
        try {
            ps.setString(1, store.getAddress());
            ps.setString(2, store.getPhone());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, Store store) throws DaoException {
        try {
            ps.setString(1, store.getAddress());
            ps.setString(2, store.getPhone());
            ps.setLong(3, store.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
