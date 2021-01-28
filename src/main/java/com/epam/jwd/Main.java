package com.epam.jwd;

import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.dao.*;
import com.epam.jwd.domain.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        TransactionHandler transactionHandler = new TransactionHandler();
        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
        UserDao userDao = daoFactory.getUserDao();

        List<User> users = transactionHandler.transactional(con ->
                userDao.getByCriteria(con, UserCriteria.builder().name("name").build()));
        System.out.println(users);
    }

}
