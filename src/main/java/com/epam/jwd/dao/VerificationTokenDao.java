package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;

public interface VerificationTokenDao {

    VerificationToken findByCriteria(Connection con, Criteria<? extends VerificationToken> criteria) throws DaoException;

    void add(Connection con, VerificationToken token) throws DaoException;

    void delete(Connection con, Long tokenId) throws DaoException;

}
