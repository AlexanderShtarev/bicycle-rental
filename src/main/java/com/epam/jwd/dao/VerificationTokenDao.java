package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface VerificationTokenDao {

    List<VerificationToken> getByCriteria(Connection con, Criteria<? extends VerificationToken> criteria) throws DaoException;

    Long add(Connection con, VerificationToken token) throws DaoException;

    void delete(Connection con, Long tokenId) throws DaoException;

    VerificationToken getByToken(Connection con, String token);

}
