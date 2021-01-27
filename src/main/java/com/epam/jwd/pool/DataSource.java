package com.epam.jwd.pool;

import java.sql.Connection;

public class DataSource {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private DataSource() {
    }

    public static Connection getConnection() throws InterruptedException {
        return pool.getConnection();
    }

    public static boolean returnConnection(Connection connection) {
        return pool.returnConnection(connection);
    }

}
