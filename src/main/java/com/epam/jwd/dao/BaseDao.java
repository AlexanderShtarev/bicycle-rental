package com.epam.jwd.dao;

import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.ConnectionPool;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * BaseDao interface, that has all main methods to work with entity.
 *
 * @param <T> The entity.
 */
public interface BaseDao<T> {

    /**
     * Method gets all entities.
     *
     * @return List of found objects.
     * @throws DaoException object if execution of query is failed.
     */
    List<T> getAll() throws DaoException;

    /**
     * Method gets entity from database by id.
     *
     * @param id the entity's id.
     * @return the entity.
     * @throws DaoException object if execution of query is failed.
     */
    Optional<T> getById(int id) throws DaoException;

    /**
     * Method insert entity in database.
     *
     * @param entity the entity.
     * @throws DaoException object if execution of query is failed.
     */
    void insert(Connection connection, T entity) throws DaoException;

    /**
     * Method update entity in database.
     *
     * @param entity the entity.
     * @throws DaoException object if execution of query is failed.
     */
    void update(Connection connection, T entity) throws DaoException;

    /**
     * Method removes entity from database by id.
     *
     * @param id entity id.
     * @throws DaoException object if execution of query is failed.
     */
    void remove(Connection connection, int id) throws DaoException;

    /**
     * Gets connection from connection pool.
     *
     * @return the connection.
     */
    default Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    /**
     * Returns connection to connection pool
     *
     * @param connection from connection pool
     */
    default void close(Connection connection) {
        ConnectionPool.getInstance().returnConnection(connection);
    }

}
