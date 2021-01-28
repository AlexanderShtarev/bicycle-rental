package com.epam.jwd.dao;

import com.epam.jwd.dao.mysql.MySqlDaoFactory;

public abstract class DaoFactory {

    public enum DaoType {
        MYSQL
    }

    public static DaoFactory getDaoFactory(DaoType db) {
        switch (db) {
            case MYSQL:
                return MySqlDaoFactory.getInstance();
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

}