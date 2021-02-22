package com.epam.jwd.dao;

import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class to handle transactions
 *
 * @see Transaction, https://www.deadcoderising.com/transactions-using-execute-around-method-in-java-8/
 */
public class TransactionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionHandler.class);

    public static void runInTransaction(Transaction transaction) throws DaoException {

        Connection dbConnection = ConnectionPool.getInstance().getConnection();

        try {
            dbConnection.setAutoCommit(false);

            LOGGER.info("Starting transaction");
            transaction.execute(dbConnection);

            LOGGER.info("Committing transaction");
            dbConnection.commit();

        } catch (Exception e) {

            try {
                dbConnection.rollback();
                LOGGER.warn("Rolling back");
            } catch (SQLException ex) {
                throw new DaoException(e);
            }
            throw new DaoException(e);

        } finally {
            try {
                dbConnection.setAutoCommit(true);
                ConnectionPool.getInstance().returnConnection(dbConnection);
            } catch (SQLException e) {
                LOGGER.error("Failed to return a connection");
            }
        }

    }

}
