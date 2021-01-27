package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.VerificationTokenDao;
import com.epam.jwd.dao.constant.VerificationTokenFieldsConstant;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.exception.DaoException;

import java.sql.*;

public class MySqlVerificationTokenDao extends GenericDao<VerificationToken> implements VerificationTokenDao {
    private static MySqlVerificationTokenDao instance;

    private static final String SQL_FIND_ALL_TOKENS =
            "SELECT id, token, created_date, user_id\n" +
                    "FROM verification_token";

    private static final String SQL_ADD_TOKEN =
            "INSERT INTO verification_token(id, token, created_date, user_id)\n" +
                    "VALUES (?, ?, ?, ?);";

    private static final String SQL_UPDATE_TOKEN =
            "UPDATE verification_token\n" +
                    "SET token = ?, created_date = ?, user_id = ?\n" +
                    "WHERE verification_token.id = ?;";

    private static final String SQL_DELETE_TOKEN =
            "DELETE verification_token FROM verification_token\n" +
                    "WHERE verification_token.id = ?;";

    public static MySqlVerificationTokenDao getInstance() {
        if (instance == null)
            instance = new MySqlVerificationTokenDao();
        return instance;
    }

    @Override
    protected VerificationToken mapToEntity(ResultSet rs) throws SQLException {

        return VerificationToken.builder()
                .id(rs.getLong(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_ID))
                .token(rs.getString(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_TOKEN))
                .createdDate(rs.getDate(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_CREATED_DATE))
                .user(User.builder()
                        .id(rs.getLong(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_USER_ID))
                        .build())
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, VerificationToken token) throws SQLException {

        ps.setString(1, token.getToken());
        ps.setDate(2, new Date(token.getCreatedDate().getTime()));
        ps.setLong(3, token.getUser().getId());

    }

    @Override
    public VerificationToken findByCriteria(Connection con, Criteria<? extends VerificationToken> criteria) throws DaoException {
        return null;
    }

    @Override
    public void add(Connection con, VerificationToken token) throws DaoException {

    }

    @Override
    public void delete(Connection con, Long tokenId) throws DaoException {

    }

}
