package com.epam.jwd.service;

import com.epam.jwd.service.impl.AuthServiceImpl;
import com.epam.jwd.service.impl.MailServiceImpl;
import com.epam.jwd.service.impl.ProductServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null)
            instance = new ServiceFactory();
        return instance;
    }

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public AuthService getAuthService() {
        return AuthServiceImpl.getInstance();
    }

    public MailService getMailService() {
        return MailServiceImpl.getInstance();
    }

    public ProductService getProductService() {
        return ProductServiceImpl.getInstance();
    }

}
