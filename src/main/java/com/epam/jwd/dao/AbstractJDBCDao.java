package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.Identified;
import com.epam.jwd.exception.DaoException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Serializable> {

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws DaoException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DaoException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DaoException;


    protected List<T> getAll(Connection con) throws DaoException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    protected List<T> getByCriteria(Connection con, Criteria<? extends Entity> criteria, QueryBuilder builder) throws DaoException {
        List<T> list;
        String sql = builder.createQuery(criteria, getSelectQuery());
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    protected T add(Connection connection, T object) throws DaoException {
        if (object.getId() != null) {
            throw new DaoException("Object is already exist.");
        }
        T persistInstance;

        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On add modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }

        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new DaoException("Exception on findByPK new data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return persistInstance;
    }

    protected void update(Connection connection, T object) throws DaoException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    protected void delete(Connection connection, PK key) throws DaoException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, key);
            } catch (Exception e) {
                throw new DaoException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}