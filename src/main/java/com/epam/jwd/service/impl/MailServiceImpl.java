package com.epam.jwd.service.impl;

import com.epam.jwd.dao.DaoFactory;
import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.dao.UserDao;
import com.epam.jwd.dao.VerificationTokenDao;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.service.MailService;

public class MailServiceImpl implements MailService {
    TransactionHandler transactionHandler;

    UserDao userDao;

    VerificationTokenDao tokenDao;

    private static MailServiceImpl instance;

    private MailServiceImpl() {
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
            userDao = daoFactory.getUserDao();
            tokenDao = daoFactory.getVerificationTokenDao();
            transactionHandler = new TransactionHandler();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static MailServiceImpl getInstance() {
        if (instance == null) {
            instance = new MailServiceImpl();
        }
        return instance;
    }

    @Override
    public void sendVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken(user);
        transactionHandler.transactional(con ->
                tokenDao.add(con, verificationToken));
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return transactionHandler.transactional(con ->
                tokenDao.getByToken(con, token));
    }

}
