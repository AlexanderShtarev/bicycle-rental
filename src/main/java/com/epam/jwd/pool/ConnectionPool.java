package com.epam.jwd.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    public static ConnectionPool instance;
    private BlockingQueue<Connection> connections;
    private static AtomicBoolean created = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    DatabaseConfig config;

    private ConnectionPool() {
        initialize();
    }

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

    private void initialize() {
        config = DatabaseConfig.getInstance();
        connections = new ArrayBlockingQueue<>(config.getPoolSize());

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < config.getPoolSize(); i++) {
            try {
                connections.add(createConnection());
            } catch (SQLException ex) {
                throw new RuntimeException("Database connection failed", ex);
            }
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                config.getUrl(), config.getUser(), config.getPassword());
    }

    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public boolean returnConnection(Connection connection) {
        if (connection != null) {
            connections.add(connection);
            return true;
        }
        return false;
    }

    public void shutdown() {
        if (created.get()) {
            lock.lock();
            try {
                for (Connection connection : connections) {
                    connection.close();
                }
                instance = null;
                created.set(false);
            } catch (SQLException exception) {
                exception.printStackTrace();
            } finally {
                lock.lock();
            }
        }
    }

}
