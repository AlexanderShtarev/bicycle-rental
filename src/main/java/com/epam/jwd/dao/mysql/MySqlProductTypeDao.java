package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.ProductTypeDao;
import com.epam.jwd.dao.constant.ProductTypeFieldsConstant;
import com.epam.jwd.domain.ProductType;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlProductTypeDao extends GenericDao<ProductType> implements ProductTypeDao {
    private static MySqlProductTypeDao instance;

    private static final String SQL_FIND_ALL_TYPES =
            "SELECT type.id, type.name\n" +
                    "FROM type\n";

    private static final String SQL_FIND_TYPE_BY_ID =
            SQL_FIND_ALL_TYPES + "WHERE id = ?";

    private static final String SQL_FIND_TYPE_BY_NAME =
            SQL_FIND_ALL_TYPES + "WHERE name = ?";

    private static final String SQL_ADD_TYPE =
            "INSERT INTO type(name)\n" +
                    "VALUES (?);";

    private static final String SQL_UPDATE_TYPE =
            "UPDATE type\n" +
                    "SET type = ?\n" +
                    "WHERE id = ?";

    private static final String SQL_DELETE_TYPE =
            "DELETE type\n" +
                    "FROM type\n" +
                    "WHERE id = ?";

    public static MySqlProductTypeDao getInstance() {
        if (instance == null)
            instance = new MySqlProductTypeDao();
        return instance;
    }

    @Override
    protected ProductType mapToEntity(ResultSet rs) throws SQLException {
        return ProductType.builder()
                .id(rs.getLong(ProductTypeFieldsConstant.TYPE_ID))
                .name(rs.getString(ProductTypeFieldsConstant.TYPE_NAME))
                .build();
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, ProductType type) throws SQLException {

        ps.setString(1, type.getName());

    }

    @Override
    public List<ProductType> findAll(Connection con) throws DaoException {
        return null;
    }

    @Override
    public ProductType findById(Connection con, Integer typeId) throws DaoException {
        return null;
    }

    @Override
    public ProductType findByName(Connection con, String typeName) throws DaoException {
        return null;
    }

    @Override
    public void add(Connection con, ProductType type) throws DaoException {

    }

    @Override
    public void update(Connection con, ProductType type) throws DaoException {

    }

    @Override
    public void delete(Connection con, Integer typeId) throws DaoException {

    }
}
