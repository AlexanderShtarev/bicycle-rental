package com.epam.jwd.dao;

import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDao<T> {

    protected GenericDao() {
    }

    protected abstract T parseResultSet(ResultSet rs) throws DaoException, SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DaoException, SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DaoException, SQLException;

    @SafeVarargs
    protected final <V> List<T> executeQuery(String sql, @SuppressWarnings("unchecked") V... values)
            throws DaoException {
        Connection connection = this.open();
        List<T> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setQueryParams(ps, values);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        this.close(connection);
        return list;
    }

    @SafeVarargs
    protected final <V> T executeForSingleResult(String queryId, V... values) {
        List<T> items = executeQuery(queryId, values);
        return items.size() == 1 ? items.get(0) : null;
    }

    protected Long add(Connection connection, String sql, T item) throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(ps, item);
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        throw new DaoException("Not added");
    }

    protected <V> void updateByField(Connection connection, String sql, T item) throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(ps, item);
            if (ps.executeUpdate() == 0)
                throw new SQLException("Not updated");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected <V> void deleteByField(Connection connection, String sql, V value) throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, value);
            if (ps.executeUpdate() == 0)
                throw new SQLException("Not deleted");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @SafeVarargs
    private <V> void setQueryParams(PreparedStatement statement, V... values) throws DaoException, SQLException {
        int parameterIndex = 1;
        statement.setObject(parameterIndex, values);
        for (Object parameter : values) {
            if (parameter != null) {
                parameterIndex += 1;
                statement.setObject(parameterIndex, parameter);
            }
        }
    }

    public Connection open() {
        try {
            return DataSource.getConnection();
        } catch (InterruptedException exception) {
            throw new DaoException(exception);
        }
    }

    public void close(Connection connection) {
        DataSource.returnConnection(connection);
    }

}
