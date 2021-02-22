package com.epam.jwd.dao;

import com.epam.jwd.domain.CreditCard;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface CreditCardDao extends BaseDao<CreditCard> {

    List<CreditCard> getUserCards(int userId) throws DaoException;

    boolean insertAllByUserId(Connection connection, List<CreditCard> userCards, int userId) throws DaoException;

    void removeUserCards(Connection con, Integer userId) throws DaoException;
}
