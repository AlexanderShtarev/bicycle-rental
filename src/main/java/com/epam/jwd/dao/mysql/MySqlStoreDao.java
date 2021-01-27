package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.Store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlStoreDao extends GenericDao<Store> {

    private static final String SQL_GET_ALL_STORES =
            "";

    private static final String SQL_ADD_STORE =
            "";

    private static final String SQL_UPDATE_STORE =
            "";

    private static final String SQL_DELETE_STORE =
            "";

    @Override
    protected Store mapToEntity(ResultSet rs) throws SQLException {

        return Store.builder()
                .id(rs.getLong("store.id"))
                .address(rs.getString("store.address"))
                .phone(rs.getString("store.phone"))
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Store store) throws SQLException {

        ps.setString(1, store.getAddress());
        ps.setString(2, store.getPhone());

    }

}
