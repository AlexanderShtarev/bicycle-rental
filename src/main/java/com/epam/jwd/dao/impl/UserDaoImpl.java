package com.epam.jwd.dao.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.UserDao;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.builder.UserQueryBuilder;
import com.epam.jwd.dao.constant.UserFieldsConstant;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends GenericDao<User> implements UserDao {
    private static UserDaoImpl instance;
    private final QueryBuilder builder = UserQueryBuilder.USER_QUERY_BUILDER;

    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id, email, password, name, balance, status_id\n" +
                    "FROM users\n";

    private static final String SQL_CREATE_USER =
            "INSERT INTO users(email, password, name, balance, status_id)\n" +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_USER =
            "UPDATE users\n" +
                    "SET id = ?, email = ?, password = ?, name = ?, balance = ?, status_id = ?\n" +
                    "WHERE users.id = ?";

    private static final String SQL_DELETE_USER =
            "DELETE FROM users\n" +
                    "WHERE users.id = ?;";

    public static UserDaoImpl getInstance() {
        if (instance == null)
            instance = new UserDaoImpl();
        return instance;
    }

    @Override
    protected User parseResultSet(ResultSet rs) throws DaoException, SQLException {
        return User.builder()
                .id(rs.getLong(UserFieldsConstant.USER_ID))
                .email(rs.getString(UserFieldsConstant.USER_EMAIL))
                .password(rs.getString(UserFieldsConstant.USER_PASSWORD))
                .name(rs.getString(UserFieldsConstant.USER_NAME))
                .balance(rs.getDouble(UserFieldsConstant.USER_BALANCE))
                .status(UserStatus.resolveStatusById(rs.getInt(UserFieldsConstant.USER_STATUS_ID)))
                .roles(new ArrayList<>())
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, User user) throws DaoException, SQLException {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setDouble(4, user.getBalance());
            ps.setInt(5, user.getStatus().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, User user) throws DaoException, SQLException {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setDouble(4, user.getBalance());
            ps.setInt(5, user.getStatus().getId());
            ps.setLong(6, user.getId());
    }


    @Override
    public List<User> getUsersByCriteria(Criteria<? extends User> criteria) {
        return null;
    }

    @Override
    public Optional<User> getSingleUserByCriteria(Criteria<? extends User> criteria) {
        return Optional.empty();
    }

    @Override
    public Object deleteById(Connection con, Long userId) {
        return null;
    }

    @Override
    public void update(Connection con, User user) {

    }

    @Override
    public Object persist(Connection con, User user) {
        return null;
    }
}
