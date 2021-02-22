package com.epam.jwd.context.config;

import com.epam.jwd.exception.InitializingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DatabaseConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
    private static DatabaseConfig instance;

    private static String url;
    private static String dbName;
    private static String user;
    private static String password;
    private static int poolSize;

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    private DatabaseConfig() {
        init();
    }

    private void init() throws InitializingException {
        LOGGER.trace("Initializing database configuration");
        try {
            ResourceBundle rb = ResourceBundle.getBundle("db");
            url = rb.getString("db.url");
            dbName = rb.getString("db.name");
            user = rb.getString("db.user");
            password = rb.getString("db.password");
            poolSize = Integer.parseInt(rb.getString("db.poolSize"));
        } catch (ExceptionInInitializerError | MissingResourceException e) {
            LOGGER.error("Error while initializing database configuration", e);
            throw new InitializingException("Error while initializing database configuration", e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolSize() {
        return poolSize;
    }

}
