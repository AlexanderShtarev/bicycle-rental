package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.*;

public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null)
            instance = new MySqlDaoFactory();
        return instance;
    }


    @Override
    public ImageDao getImageDao() {
        return null;
    }

    @Override
    public InventoryDao getInventoryDao() {
        return null;
    }

    @Override
    public ProductDao getProductDao() {
        return null;
    }

    @Override
    public ProductProducerDao getProductProducerDao() {
        return null;
    }

    @Override
    public ProductTypeDao getProductTypeDao() {
        return null;
    }

    @Override
    public RentalDao getRentalDao() {
        return null;
    }

    @Override
    public StoreDao getStoreDao() {
        return null;
    }

    @Override
    public UserDao getUserDao() {
        return null;
    }

    @Override
    public UserRoleDao getRoleDao() {
        return null;
    }

    @Override
    public VerificationTokenDao getVerificationTokenDao() {
        return null;
    }

}