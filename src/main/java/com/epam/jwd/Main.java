package com.epam.jwd;

import com.epam.jwd.pool.DataSource;
import com.epam.jwd.pool.DatabaseConfig;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        try {
            DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
            System.out.println(databaseConfig.toString());
            Connection connection = DataSource.getConnection();
            System.out.println(connection);
            DataSource.returnConnection(connection);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }

}
