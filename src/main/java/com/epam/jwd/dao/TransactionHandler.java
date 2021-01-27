package com.epam.jwd.dao;

import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandler {

    public <T> T transactional(Transaction<T> transaction) throws DaoException {
        Connection connection;

        try {
            connection = DataSource.getConnection();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DaoException(e);
        }

        try {
            connection.setAutoCommit(false);
            T result = transaction.execute(connection);
            connection.commit();
            return result;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                DataSource.returnConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}