package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.TokenDao;
import com.epam.jwd.domain.Token;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TokenDaoImpl extends AbstractDao<Token> implements TokenDao {
    private static final int ID_PARAMETER_INDEX = 4;
    private static TokenDaoImpl instance;

    private static final String SQL_SELECT_TOKEN_BY_TOKEN =
            "SELECT verification_token.id, verification_token.token, \n" +
                    "       verification_token.created_date, verification_token.user_id\n" +
                    "FROM verification_token WHERE token = ?";

    private static final String SQL_CREATE_TOKEN =
            "INSERT INTO verification_token(token, created_date, user_id)\n" +
                    "VALUES (?, ?, ?);";

    private static final String SQL_UPDATE_TOKEN =
            "UPDATE verification_token\n" +
                    "SET token = ?, created_date = ?, user_id = ?\n" +
                    "WHERE verification_token.id = ?;";

    private static final String SQL_DELETE_TOKEN =
            "DELETE verification_token FROM verification_token\n" +
                    "WHERE verification_token.id = ?;";

    public static TokenDao getInstance() {
        if (instance == null) {
            instance = new TokenDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Token> getAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Token> getById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(Connection connection, Token entity) throws DaoException {
        super.insert(connection, SQL_CREATE_TOKEN, entity);
    }

    @Override
    public void update(Connection connection, Token entity) throws DaoException {
        super.updateByField(connection, SQL_UPDATE_TOKEN, entity, ID_PARAMETER_INDEX, entity.getId());
    }

    @Override
    public void remove(Connection connection, int id) throws DaoException {
        super.deleteByField(connection, SQL_DELETE_TOKEN, id);
    }

    @Override
    protected Token mapToEntity(ResultSet rs) throws SQLException {
        return Token.builder()
                .id(rs.getInt("verification_token.id"))
                .token(rs.getString("verification_token.token"))
                .createdDate(rs.getDate("verification_token.created_date"))
                .user(User.builder().id(rs.getInt("verification_token.user_id")).build())
                .build();
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Token entity) throws SQLException {
        ps.setString(1, entity.getToken());
        ps.setDate(2, new java.sql.Date(entity.getCreatedDate().getTime()));
        ps.setInt(3, entity.getUser().getId());
    }

    @Override
    public Optional<Token> getTokenByInputToken(String token) throws DaoException {
        return super.getByField(SQL_SELECT_TOKEN_BY_TOKEN, token).stream().findAny();
    }

}
