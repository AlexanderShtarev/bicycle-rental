package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Class, that helps DAO's to provide access to the database and deal with application entities.
 *
 * @param <T> the entity type.
 * @see Connection
 * @see Entity
 */
public abstract class AbstractDao<T extends Entity> implements BaseDao<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);

    /**
     * Abstract method, maps rs to entity
     *
     * @param rs
     */
    protected abstract T mapToEntity(ResultSet rs) throws SQLException;

    /**
     * Abstract method, maps ps to insert or update
     *
     * @param ps
     * @param entity that need to mapped
     */
    protected abstract void mapFromEntity(PreparedStatement ps, T entity) throws SQLException;

    /**
     * Method gets all entities by sql query
     *
     * @param sqlQuery concrete query from dao implementations
     * @return List of entities
     */
    protected List<T> getAll(String sqlQuery) throws DaoException {
        Connection connection = this.getConnection();
        List<T> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            LOGGER.warn("Exception while finding all entities: ", e);
            throw new DaoException("Exception while finding all entities: ", e);
        } finally {
            this.close(connection);
        }
        return list;
    }

    /**
     * Methods gets all entities that has concrete value
     *
     * @param sqlQuery concrete query from dao implementations
     * @param value    select parameter
     * @return List of entities
     */
    protected final <V> List<T> getByField(String sqlQuery, V value)
            throws DaoException {

        Connection connection = this.getConnection();
        List<T> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setObject(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            LOGGER.warn("Exception while finding by fields: ", e);
            throw new DaoException("Exception while finding by fields: ", e);
        } finally {
            this.close(connection);
        }
        return list;
    }

    /**
     * Method helps DAO's to insert entity, that has connection as parameter, because of we need to do insert
     * in transaction
     *
     * @param connection from connection pool
     * @param sqlQuery   query
     * @param item       that need's to be inserted
     */
    protected void insert(Connection connection, String sqlQuery, T item) throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            mapFromEntity(ps, item);
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    item.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.warn("Exception while insert entity: ", e);
            throw new DaoException("Exception while insert entity: ", e);
        }
    }

    /**
     * Method helps DAO's to update entity, that has connection as parameter, because of we need to do update
     * in transaction
     *
     * @param connection     from connection pool
     * @param sqlQuery       query
     * @param item           that need's to be inserted
     * @param parameterIndex index of field in database by which we need to insert entity 'WHERE ?(index) = ?(value)'
     * @param value          of WHERE parameter
     */
    protected <V> void updateByField(Connection connection, String sqlQuery, T item, int parameterIndex, V value)
            throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            mapFromEntity(ps, item);
            ps.setObject(parameterIndex, value);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("Exception while updating entity: ", e);
            throw new DaoException("Exception while updating entity: ", e);
        }
    }

    /**
     * Method that helps DAO's to delete entity from database
     *
     * @param connection from con pool
     * @param sqlQuery   query
     * @param value      by which entity will be deleted 'WHERE ? = value'
     */
    protected <V> void deleteByField(Connection connection, String sqlQuery, V value) throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setObject(1, value);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("Exception while deleting entity:", e);
            throw new DaoException("Exception while deleting entity", e);
        }
    }

}
