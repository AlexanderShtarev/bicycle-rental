package com.epam.jwd.dao.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.ProductTypeDao;
import com.epam.jwd.dao.builder.UserQueryBuilder;
import com.epam.jwd.dao.constant.ProductTypeFieldsConstant;
import com.epam.jwd.domain.ProductType;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductTypDaoImpl extends GenericDao<ProductType> implements ProductTypeDao {
    private static ProductTypeDao instance;

    private static final String SQL_SELECT_ALL_TYPES =
            "SELECT type.id, type.name\n" +
                    "FROM type\n";

    private static final String SQL_CREATE_TYPE =
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

    public static ProductTypeDao getInstance() {
        if (instance == null)
            instance = new ProductTypDaoImpl();
        return instance;
    }

    @Override
    protected ProductType parseResultSet(ResultSet rs) throws DaoException, SQLException {
        return ProductType.builder()
                .id(rs.getLong(ProductTypeFieldsConstant.TYPE_ID))
                .name(rs.getString(ProductTypeFieldsConstant.TYPE_NAME))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, ProductType type) throws DaoException, SQLException {
            ps.setString(1, type.getName());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, ProductType type) throws DaoException, SQLException {
            ps.setString(1, type.getName());
            ps.setLong(2, type.getId());
    }

    @Override
    public Optional<ProductType> findSingleTypeByCriteria(Criteria<? extends ProductType> criteria) {
        return Optional.empty();
    }

    @Override
    public ProductType findTypeByProductId() {
        return null;
    }

    @Override
    public List<ProductType> findAllTypesByCriteria(Criteria<? extends ProductType> criteria) {
        return null;
    }

    @Override
    public ProductType persist(Connection con, ProductType type) {
        return null;
    }

}
