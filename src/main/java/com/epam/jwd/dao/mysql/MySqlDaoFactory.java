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
    public InventoryDao getInventoryDao() {
        return MySqlInventoryDao.getInstance();
    }

    @Override
    public ProductDao getProductDao() {
        return MySqlProductDao.getInstance();
    }

    @Override
    public ProductProducerDao getProductProducerDao() {
        return MySqlProductProducerDao.getInstance();
    }

    @Override
    public ProductTypeDao getProductTypeDao() {
        return MySqlProductTypeDao.getInstance();
    }

    @Override
    public RentalDao getRentalDao() {
        return MySqlRentalDao.getInstance();
    }

    @Override
    public StoreDao getStoreDao() {
        return MySqlStoreDao.getInstance();
    }

    @Override
    public UserDao getUserDao() {
        return MySqlUserDao.getInstance();
    }

    @Override
    public VerificationTokenDao getVerificationTokenDao() {
        return MySqlVerificationTokenDao.getInstance();
    }

    @Override
    public UserRoleDao getUserRoleDao() {
        return MySqlUserRoleDao.getInstance();
    }

}