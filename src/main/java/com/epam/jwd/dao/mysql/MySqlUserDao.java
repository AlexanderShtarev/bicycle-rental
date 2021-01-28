package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.AbstractJDBCDao;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySqlUserDao extends AbstractJDBCDao<User, Long> implements UserDao {
    private static MySqlUserDao instance;

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

    public static MySqlUserDao getInstance() {
        if (instance == null)
            instance = new MySqlUserDao();
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SQL_SELECT_ALL_USERS;
    }

    @Override
    public String getCreateQuery() {
        return SQL_CREATE_USER;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_USER;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_USER;
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws DaoException {
        List<User> list = new LinkedList<>();
        try {
            while (rs.next()) {
                User user = User.builder()
                        .id(rs.getLong(UserFieldsConstant.USER_ID))
                        .email(rs.getString(UserFieldsConstant.USER_EMAIL))
                        .password(rs.getString(UserFieldsConstant.USER_PASSWORD))
                        .name(rs.getString(UserFieldsConstant.USER_NAME))
                        .balance(rs.getDouble(UserFieldsConstant.USER_BALANCE))
                        .status(UserStatus.resolveStatusById(rs.getInt(UserFieldsConstant.USER_STATUS_ID)))
                        .roles(new ArrayList<>())
                        .build();
                list.add(user);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, User user) throws DaoException {
        try {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setDouble(4, user.getBalance());
            ps.setInt(5, user.getStatus().getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, User user) throws DaoException {
        try {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setDouble(4, user.getBalance());
            ps.setInt(5, user.getStatus().getId());
            ps.setLong(6, user.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> getAll(Connection con) throws DaoException {
        return super.getAll(con);
    }

    @Override
    public List<User> getByCriteria(Connection con, Criteria<? extends User> criteria) throws DaoException {
        QueryBuilder builder = new UserQueryBuilder();
        return super.getByCriteria(con, criteria, builder);
    }

    @Override
    public Long add(Connection con, User user) throws DaoException {
        Long id = super.add(con, user);
        user.setId(id);
        return id;
    }

    @Override
    public void update(Connection con, User user) throws DaoException {
        super.update(con, user);
    }

    @Override
    public void delete(Connection con, Long userId) throws DaoException {
        super.delete(con, userId);
    }

}
