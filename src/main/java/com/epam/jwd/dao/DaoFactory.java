package com.epam.jwd.dao;

import com.epam.jwd.dao.impl.MySqlDaoFactory;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.DataSource;

import java.sql.Connection;

public abstract class DaoFactory {

    public enum DaoType {
        MYSQL
    }

    public static DaoFactory getDaoFactory(DaoType db) {
        switch (db) {
            case MYSQL:
                return MySqlDaoFactory.MY_SQL_DAO_FACTORY;
            default:
                throw new IllegalArgumentException();
        }
    }

    public abstract InventoryDao getInventoryDao();

    public abstract ProductDao getProductDao();

    public abstract ProductProducerDao getProductProducerDao();

    public abstract ProductTypeDao getProductTypeDao();

    public abstract RentalDao getRentalDao();

    public abstract StoreDao getStoreDao();

    public abstract UserDao getUserDao();

    public abstract VerificationTokenDao getVerificationTokenDao();
    
    public abstract UserRoleDao getUserRoleDao();

}