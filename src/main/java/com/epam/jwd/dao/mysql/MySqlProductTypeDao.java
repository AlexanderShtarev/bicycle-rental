package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.AbstractJDBCDao;
import com.epam.jwd.dao.ProductTypeDao;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.constant.ProductTypeFieldsConstant;
import com.epam.jwd.domain.ProductType;
import com.epam.jwd.exception.DaoException;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MySqlProductTypeDao extends AbstractJDBCDao<ProductType, Long> implements ProductTypeDao {
    private static MySqlProductTypeDao instance;
    private QueryBuilder builder;

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

    public static MySqlProductTypeDao getInstance() {
        if (instance == null)
            instance = new MySqlProductTypeDao();
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SQL_SELECT_ALL_TYPES;
    }

    @Override
    public String getCreateQuery() {
        return SQL_CREATE_TYPE;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_TYPE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_TYPE;
    }

    @Override
    protected List<ProductType> parseResultSet(ResultSet rs) throws DaoException {
        List<ProductType> list = new LinkedList<>();
        try {
            while (rs.next()) {
                ProductType type = ProductType.builder()
                        .id(rs.getLong(ProductTypeFieldsConstant.TYPE_ID))
                        .name(rs.getString(ProductTypeFieldsConstant.TYPE_NAME))
                        .build();
                list.add(type);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, ProductType type) throws DaoException {
        try {
            ps.setString(1, type.getName());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, ProductType type) throws DaoException {
        try {
            ps.setString(1, type.getName());
            ps.setLong(2, type.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ProductType> getAll(Connection con) throws DaoException {
        return super.getAll(con);
    }

    @Override
    public List<ProductType> getByCriteria(Connection con, Criteria<? extends ProductType> criteria) throws DaoException {
        return super.getByCriteria(con, criteria, builder);
    }

    @Override
    public ProductType add(Connection con, ProductType type) throws DaoException {
        return super.add(con, type);
    }

    @Override
    public void update(Connection con, ProductType type) throws DaoException {
        super.update(con, type);
    }

    @Override
    public void delete(Connection con, Long typeId) throws DaoException {
        super.delete(con, typeId);
    }

}
