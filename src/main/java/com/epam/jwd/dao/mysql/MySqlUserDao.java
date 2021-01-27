package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.UserDao;
import com.epam.jwd.dao.constant.UserFieldsConstant;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends GenericDao<User> implements UserDao {
    private static MySqlUserDao instance;

    private static final String SQL_GET_ALL_USERS =
            "SELECT id, login, password, name, balance, status_id\n" +
                    "FROM users;\n";

    private static final String SQL_ADD_USER =
            "INSERT INTO users(id, login, password, name, balance, status_id)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_USER =
            "UPDATE users\n" +
                    "SET id = ?, login = ?, password = ?, name = ?, balance = ?, status_id = ?\n" +
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
    protected User mapToEntity(ResultSet rs) throws SQLException {

        return User.builder()
                .id(rs.getLong(UserFieldsConstant.USER_ID))
                .email(rs.getString(UserFieldsConstant.USER_EMAIL))
                .password(rs.getString(UserFieldsConstant.USER_PASSWORD))
                .name(rs.getString(UserFieldsConstant.USER_NAME))
                .balance(rs.getDouble(UserFieldsConstant.USER_BALANCE))
                .status(UserStatus.resolveStatusById(rs.getInt(UserFieldsConstant.USER_STATUS_ID)))
                .roles(new ArrayList<UserRole>())
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, User user) throws SQLException {

        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setDouble(4, user.getBalance());
        ps.setInt(5, user.getStatus().getId());

    }

    @Override
    public List<User> findAll(Connection con) throws DaoException {
        return null;
    }

    @Override
    public User findByCriteria(Connection con, Criteria<? extends User> criteria) throws DaoException {
        return null;
    }

    @Override
    public void addUser(Connection con, User user) throws DaoException {

    }

    @Override
    public void updateUser(Connection con, User user) throws DaoException {

    }

    @Override
    public void deleteUser(Connection con, Long userId) throws DaoException {

    }
}
