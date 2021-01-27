package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlProductTypeDao extends GenericDao<ProductType> {

    private static final String SQL_GET_ALL_TYPES =
            "";

    private static final String SQL_ADD_TYPE =
            "";

    private static final String SQL_UPDATE_TYPE =
            "";

    private static final String SQL_DELETE_TYPE =
            "";

    @Override
    protected ProductType mapToEntity(ResultSet rs) throws SQLException {
        return ProductType.builder()
                .id(rs.getLong("type.id"))
                .name(rs.getString("type.name"))
                .build();
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, ProductType type) throws SQLException {

        ps.setString(1, type.getName());

    }

}
