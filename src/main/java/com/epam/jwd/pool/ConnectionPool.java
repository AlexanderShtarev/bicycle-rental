package com.epam.jwd.pool;

import com.epam.jwd.context.config.DatabaseConfig;
import com.epam.jwd.exception.InitializingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread safe connection pool.
 *
 * @see BlockingQueue
 * @see ReentrantLock
 * @see AtomicBoolean
 * @see DatabaseConfig
 */
public class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    public static ConnectionPool instance;
    private BlockingQueue<Connection> connections;
    private static AtomicBoolean created = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    DatabaseConfig config;

    private ConnectionPool() {
        init();
    }

    /**
     * Get instance of connection pool class.
     *
     * @return instance.
     */
    public static ConnectionPool getInstance() {
        if (!created.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    created.set(true);
                }
            } finally {
                lock.lock();
            }
        }
        return instance;
    }

    private void init() throws InitializingException {
        LOGGER.trace("Initializing connection pool");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can't class for name: ", e);
            throw new InitializingException("Initializing connection pool failed", e);
        }
        initConnectionPool();
    }

    /**
     * Initializes connection pool, adds connection to BlockingQueue
     */
    private void initConnectionPool() throws InitializingException {
        this.config = DatabaseConfig.getInstance();
        this.connections = new ArrayBlockingQueue<>(config.getPoolSize());
        for (int i = 0; i < config.getPoolSize(); i++) {
            try {
                connections.add(this.createConnection());
            } catch (SQLException ex) {
                LOGGER.error("Can't add connections", ex);
                throw new InitializingException("Database connection failed", ex);
            }
        }
    }

    /**
     * Creates connection
     *
     * @return connection
     */
    private Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                config.getUrl() + config.getDbName(), config.getUser(), config.getPassword());
        LOGGER.trace("Connection created");
        return connection;
    }

    /**
     * Getting connection
     *
     * @return connection from connection pool
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.warn("Connection interrupted: ", e);
        }
        LOGGER.trace("Connection got");
        return connection;
    }

    /**
     * Returns connection to connection pool
     *
     * @param connection from connection pool
     */
    public void returnConnection(Connection connection) {
        if (connection != null) {
            connections.add(connection);
            LOGGER.trace("Connection returned");
        } else {
            LOGGER.warn("Can't return connection, possible connection leaking");
        }
    }

    /**
     * Shutdowns connection pool, removing all connections
     */
    public void shutdown() {
        if (created.get()) {
            lock.lock();
            try {
                for (Connection connection : connections) {
                    connection.close();
                }
                instance = null;
                created.set(false);
                LOGGER.debug("Pool shutdowns");
            } catch (SQLException exception) {
                LOGGER.error("Shutdown failed: ", exception);
                exception.printStackTrace();
            } finally {
                lock.lock();
            }
        }
    }

}
