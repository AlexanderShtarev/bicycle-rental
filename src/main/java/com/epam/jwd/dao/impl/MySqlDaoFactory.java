package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.*;

public class MySqlDaoFactory extends DaoFactory {
    public static final MySqlDaoFactory MY_SQL_DAO_FACTORY = new MySqlDaoFactory();

    @Override
    public InventoryDao getInventoryDao() {
        return InventoryDaoImpl.getInstance();
    }

    @Override
    public ProductDao getProductDao() {
        return ProductDaoImpl.getInstance();
    }

    @Override
    public ProductProducerDao getProductProducerDao() {
        return ProductProducerDaoImpl.getInstance();
    }

    @Override
    public ProductTypeDao getProductTypeDao() {
        return ProductTypDaoImpl.getInstance();
    }

    @Override
    public RentalDao getRentalDao() {
        return RentalDaoImpl.getInstance();
    }

    @Override
    public StoreDao getStoreDao() {
        return StoreDaoImpl.getInstance();
    }

    @Override
    public UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    @Override
    public VerificationTokenDao getVerificationTokenDao() {
        return VerificationTokenDaoImpl.getInstance();
    }

    @Override
    public UserRoleDao getUserRoleDao() {
        return UserRoleDaoImpl.getInstance();
    }

}
