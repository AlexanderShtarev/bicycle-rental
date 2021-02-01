package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

public interface VerificationTokenDao {

    Optional<VerificationToken> getSingleTokenByCriteria(Criteria<? extends VerificationToken> criteria) throws DaoException;

    void add(Connection con, VerificationToken verificationToken) throws DaoException;

    Object persist(Connection con, VerificationToken verificationToken);
}
