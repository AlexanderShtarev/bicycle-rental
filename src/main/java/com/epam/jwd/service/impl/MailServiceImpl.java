package com.epam.jwd.service.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.VerificationTokenCriteria;
import com.epam.jwd.dao.DaoFactory;
import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.dao.UserDao;
import com.epam.jwd.dao.VerificationTokenDao;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.service.MailService;
import com.epam.jwd.service.UserService;

import java.util.List;

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
    public void send() {

    }

    @Override
    public VerificationToken findTokenByCriteria(Criteria<? extends VerificationToken> criteria) {
        return transactionHandler.transactional(con -> {
            VerificationToken token = null;
            List<VerificationToken> tokens = tokenDao.getByCriteria(con, criteria);
            if (!tokens.isEmpty()) {
                token = tokens.get(0);
            }
            return token;
        });
    }

    @Override
    public boolean validateToken(VerificationToken token, User user) {
            return token.getUser().getId().equals(user.getId());
    }

    @Override
    public VerificationToken createVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken(user);
        return transactionHandler.transactional(con -> {
                tokenDao.add(con, verificationToken);
                return verificationToken;
        });
    }

}
