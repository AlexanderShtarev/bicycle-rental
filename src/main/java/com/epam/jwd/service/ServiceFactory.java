package com.epam.jwd.service;

import com.epam.jwd.service.impl.*;

public class ServiceFactory {
    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public ProductService getProductService() {
        return ProductServiceImpl.getInstance();
    }

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public RentalService getRentalService() {
        return RentalServiceImpl.getInstance();
    }

    public CartService getCartService() {
        return CartServiceImpl.getInstance();
    }

}
