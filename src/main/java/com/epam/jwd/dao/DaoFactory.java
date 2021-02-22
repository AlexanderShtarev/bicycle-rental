package com.epam.jwd.dao;

import com.epam.jwd.dao.impl.*;

public class DaoFactory {
    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public CategoryDao getCategoryDao() {
        return CategoryDao.getInstance();
    }

    public ManufacturerDao getManufacturerDao() {
        return ManufacturerDao.getInstance();
    }

    public ProductDao getProductDao() {
        return ProductDaoImpl.getInstance();
    }

    public UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    public UserRoleDao getUserRoleDao() {
        return UserRoleDaoImpl.getInstance();
    }

    public CreditCardDao getCreditCardDao() {
        return CreditCardDaoImpl.getInstance();
    }

    public RentalItemDao getRentalItemDao() {
        return RentalItemDaoImpl.getInstance();
    }

    public RentalDao getRentalDao() {
        return RentalDaoImpl.getInstance();
    }

    public ShoppingCartDao getShoppingCartDao() {
        return ShoppingCartDaoImpl.getInstance();
    }

    public ShoppingCartItemDao getShoppingCartItemDao() {
        return ShoppingCartItemDaoImpl.getInstance();
    }

    public TokenDao getTokenDao() {
        return TokenDaoImpl.getInstance();
    }
}
