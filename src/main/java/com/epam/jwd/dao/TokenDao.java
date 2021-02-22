package com.epam.jwd.dao;

import com.epam.jwd.domain.Token;
import com.epam.jwd.exception.DaoException;

import java.util.Optional;

public interface TokenDao extends BaseDao<Token> {

    Optional<Token> getTokenByInputToken(String token) throws DaoException;

}
