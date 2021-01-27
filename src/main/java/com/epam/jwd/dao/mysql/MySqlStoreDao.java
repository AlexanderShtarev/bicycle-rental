package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.StoreDao;
import com.epam.jwd.dao.constant.StoreFieldsConstant;
import com.epam.jwd.domain.Store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlStoreDao extends GenericDao<Store> implements StoreDao {
    private static MySqlStoreDao instance;

    private static final String SQL_GET_ALL_STORES =
            "SELECT id, address, phone\n" +
                    "FROM store";

    private static final String SQL_ADD_STORE =
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
    protected Store mapToEntity(ResultSet rs) throws SQLException {

        return Store.builder()
                .id(rs.getLong(StoreFieldsConstant.STORE_ID))
                .address(rs.getString(StoreFieldsConstant.STORE_ADDRESS))
                .phone(rs.getString(StoreFieldsConstant.STORE_PHONE))
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Store store) throws SQLException {

        ps.setString(1, store.getAddress());
        ps.setString(2, store.getPhone());

    }

}
