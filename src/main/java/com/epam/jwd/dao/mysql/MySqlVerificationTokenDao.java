package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.AbstractJDBCDao;
import com.epam.jwd.dao.VerificationTokenDao;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.builder.VerificationTokenQueryBuilder;
import com.epam.jwd.dao.constant.VerificationTokenFieldsConstant;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MySqlVerificationTokenDao extends AbstractJDBCDao<VerificationToken, Long> implements VerificationTokenDao {
    private static MySqlVerificationTokenDao instance;
    private QueryBuilder builder = VerificationTokenQueryBuilder.VERIFICATION_TOKEN_QUERY_BUILDER;

    private static final String SQL_SELECT_ALL_TOKENS =
            "SELECT id, token, created_date, user_id\n" +
                    "FROM verification_token";

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

    public static MySqlVerificationTokenDao getInstance() {
        if (instance == null)
            instance = new MySqlVerificationTokenDao();
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SQL_SELECT_ALL_TOKENS;
    }

    @Override
    public String getCreateQuery() {
        return SQL_CREATE_TOKEN;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_TOKEN;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_TOKEN;
    }

    @Override
    protected String getCountQuery() {
        return null;
    }

    @Override
    protected List<VerificationToken> parseResultSet(ResultSet rs) throws DaoException {
        List<VerificationToken> list = new LinkedList<>();
        try {
            while (rs.next()) {
                VerificationToken token = VerificationToken.builder()
                        .id(rs.getLong(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_ID))
                        .token(rs.getString(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_TOKEN))
                        .createdDate(rs.getDate(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_CREATED_DATE))
                        .user(User.builder()
                                .id(rs.getLong(VerificationTokenFieldsConstant.VERIFICATION_TOKEN_USER_ID))
                                .build())
                        .build();
                list.add(token);
            }
        } catch (Exception e) {
            throw new DaoException();
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, VerificationToken token) throws DaoException {
        try {
            ps.setString(1, token.getToken());
            ps.setDate(2, new Date(token.getCreatedDate().getTime()));
            ps.setLong(3, token.getUser().getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, VerificationToken token) throws DaoException {
        try {
            ps.setString(1, token.getToken());
            ps.setDate(2, new Date(token.getCreatedDate().getTime()));
            ps.setLong(3, token.getUser().getId());
            ps.setLong(4, token.getId());
        } catch (Exception e) {
            throw new DaoException();
        }
    }

    @Override
    public List<VerificationToken> getByCriteria(Connection con, Criteria<? extends VerificationToken> criteria) throws DaoException {
        return super.getByCriteria(con, criteria, builder);
    }

    @Override
    public Long add(Connection con, VerificationToken token) throws DaoException {
        Long id = super.add(con, token);
        /*token.setId(id);*/
        return id;
    }

    @Override
    public void delete(Connection con, Long tokenId) throws DaoException {
        super.delete(con, tokenId);
    }

    @Override
    public VerificationToken getByToken(Connection con, String token) {
        return null;
    }

}
