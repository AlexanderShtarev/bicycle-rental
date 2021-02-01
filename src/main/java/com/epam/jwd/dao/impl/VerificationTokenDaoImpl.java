package com.epam.jwd.dao.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.VerificationTokenDao;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.builder.VerificationTokenQueryBuilder;
import com.epam.jwd.dao.constant.UserFieldsConstant;
import com.epam.jwd.dao.constant.VerificationTokenFieldsConstant;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class VerificationTokenDaoImpl extends GenericDao<VerificationToken> implements VerificationTokenDao {
    private static VerificationTokenDao instance;
    private QueryBuilder builder = VerificationTokenQueryBuilder.VERIFICATION_TOKEN_QUERY_BUILDER;

    private static final String SQL_SELECT_ALL_TOKENS =
            "SELECT verification_token.id, verification_token.token, \n" +
                    "       verification_token.created_date, verification_token.user_id,\n" +
                    "       users.id, users.email, users.password, users.name, users.balance, users.status_id,\n" +
                    "       user_status.id, user_status.name\n" +
                    "FROM verification_token\n" +
                    "JOIN users on users.id = verification_token.user_id\n" +
                    "JOIN user_status on users.status_id = user_status.id;";

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

    public static VerificationTokenDao getInstance() {
        if (instance == null)
            instance = new VerificationTokenDaoImpl();
        return instance;
    }


    @Override
    protected VerificationToken parseResultSet(ResultSet rs) throws DaoException, SQLException {
        return VerificationToken.builder()
                .id(rs.getLong(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_ID))
                .token(rs.getString(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_TOKEN))
                .createdDate(rs.getDate(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_CREATED_DATE))
                .user(User.builder()
                        .id(rs.getLong(UserFieldsConstant.USER_ID))
                        .email(rs.getString(UserFieldsConstant.USER_EMAIL))
                        .password(rs.getString(UserFieldsConstant.USER_PASSWORD))
                        .name(rs.getString(UserFieldsConstant.USER_NAME))
                        .balance(rs.getDouble(UserFieldsConstant.USER_BALANCE))
                        .status(UserStatus.resolveStatusById(rs.getInt(UserFieldsConstant.USER_STATUS_ID)))
                        .roles(new ArrayList<>())
                        .build())
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, VerificationToken token) throws DaoException, SQLException {
        ps.setString(1, token.getToken());
        ps.setDate(2, new Date(token.getCreatedDate().getTime()));
        ps.setLong(3, token.getUser().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, VerificationToken token) throws DaoException, SQLException {
        ps.setString(1, token.getToken());
        ps.setDate(2, new Date(token.getCreatedDate().getTime()));
        ps.setLong(3, token.getUser().getId());
        ps.setLong(4, token.getId());
    }

    @Override
    public Optional<VerificationToken> getSingleTokenByCriteria(Criteria<? extends VerificationToken> criteria) throws DaoException {
        String sql = builder.createQuery(criteria, SQL_SELECT_ALL_TOKENS);
        return Optional.ofNullable(super.executeForSingleResult(sql));
    }

    @Override
    public void add(Connection con, VerificationToken verificationToken) throws DaoException {
        Long id = super.add(con, SQL_CREATE_TOKEN, verificationToken);
        verificationToken.setId(id);
    }

    @Override
    public Object persist(Connection con, VerificationToken verificationToken) {
        return null;
    }

}
