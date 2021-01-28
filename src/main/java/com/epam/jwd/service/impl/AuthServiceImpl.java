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

    @Override
    public boolean login(String password, String hashPassword) {
        return SCryptUtil.check(password, hashPassword);
    }

    @Override
    public boolean register(User user) {
        boolean result = false;
        boolean userExist = checkIfUserExist(user.getEmail());
        if (!userExist) {
            scryptPassword(user);
            result = addUser(user);
        }
        return result;
    }

    @Override
    public boolean checkIfUserExist(String email) {
        UserCriteria criteria = UserCriteria.builder().email(email).build();
        return transactionHandler.transactional(con -> {
            List<User> existingUser = userDao.getByCriteria(con, criteria);
            return existingUser.size() > 0;
        });
    }

    private boolean addUser(User user) {
        return transactionHandler.transactional(con -> {
            userDao.add(con, user);
            //todo
            return true;
        });
    }

    private void scryptPassword(User user) {
        String hashed = SCryptUtil.scrypt(user.getPassword(), 16, 16, 16);
        user.setPassword(hashed);
    }


}
