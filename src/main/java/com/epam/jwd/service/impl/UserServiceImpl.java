package com.epam.jwd.service.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.dao.DaoFactory;
import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.dao.UserDao;
import com.epam.jwd.dao.VerificationTokenDao;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    TransactionHandler transactionHandler;

    UserDao userDao;

    private static UserServiceImpl instance;

    private UserServiceImpl() {
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
            userDao = daoFactory.getUserDao();
            transactionHandler = new TransactionHandler();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User findUserByEmail(String email) {
        UserCriteria criteria = UserCriteria.builder().email(email).build();
        return transactionHandler.transactional(con -> {
            User user = null;
            List<User> userList = new ArrayList<>();
            try {
                userList = userDao.getByCriteria(con, criteria);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            if (!userList.isEmpty()) {
                user = userList.get(0);
            }
            return user;
        });
    }

    @Override
    public boolean addUser(User user) {
        return transactionHandler.transactional(con -> {
            try {
                userDao.add(con, user);
                //todo roleDao.addUserRoles(user);
            } catch (DaoException e) {
                e.printStackTrace();
            }
            return true;
        });
    }

    @Override
    public List<User> findByCriteria(Criteria<? extends User> criteria) {
        return transactionHandler.transactional(con ->
                userDao.getByCriteria(con, criteria));
    }


}
