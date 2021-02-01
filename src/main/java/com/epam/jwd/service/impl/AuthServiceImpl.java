package com.epam.jwd.service.impl;

import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.dao.DaoFactory;
import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.dao.UserDao;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.AuthService;
import com.epam.jwd.service.UserService;
import com.lambdaworks.crypto.SCryptUtil;

import java.util.List;

public class AuthServiceImpl implements AuthService {
    TransactionHandler transactionHandler;

    UserDao userDao;

    private static AuthServiceImpl instance;

    private AuthServiceImpl() {
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
            userDao = daoFactory.getUserDao();
            transactionHandler = new TransactionHandler();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthServiceImpl();
        }
        return instance;
    }


}
