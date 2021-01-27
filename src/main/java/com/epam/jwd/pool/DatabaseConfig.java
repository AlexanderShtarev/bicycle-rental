package com.epam.jwd.pool;

import java.util.ResourceBundle;

public class DatabaseConfig {
    private static DatabaseConfig instance;

    private String url;
    private String dbName;
    private String user;
    private String password;
    private int poolSize;

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public DatabaseConfig() {
        init();
    }

    public void init() {
        ResourceBundle rb = ResourceBundle.getBundle("database");
        this.url = rb.getString("db.url");
        this.dbName = rb.getString("db.name");
        this.user = rb.getString("db.user");
        this.password = rb.getString("db.password");
        this.poolSize = Integer.parseInt(rb.getString("db.poolSize"));
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

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "url='" + url + '\'' +
                ", dbName='" + dbName + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", poolSize=" + poolSize +
                '}';
    }

}
