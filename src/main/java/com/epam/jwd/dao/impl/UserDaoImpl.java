package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.UserDao;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final int ID_PARAMETER_INDEX = 7;
    private static UserDaoImpl instance = null;

    private static final String SQL_GET_USERS =
            "SELECT users.id, users.name, users.surname, users.email, users.password,\n" +
                    "users.balance, users.status_id FROM users\n";

    private static final String SQL_GET_BY_EMAIL =
            SQL_GET_USERS + "WHERE email = ?\n";

    private static final String SQL_GET_BY_ID =
            SQL_GET_USERS + "WHERE id = ?\n";

    private static final String SQL_INSERT_USER =
            "INSERT INTO users(name, surname, email, password, balance, status_id) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?)\n";

    private static final String SQL_UPDATE_USER =
            "UPDATE users SET name = ?, surname = ?, email = ?, password = ?, balance = ?, status_id = ?\n" +
                    " WHERE users.id = ?";

    private static final String SQL_REMOVE_USER = "DELETE FROM users WHERE users.id = ?";

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> getById(int id) throws DaoException {
        return super.getByField(SQL_GET_BY_ID, id).stream()
                .filter(Objects::nonNull).findFirst();
    }

    @Override
    public List<User> getAll() throws DaoException {
        return super.getAll(SQL_GET_USERS);
    }

    @Override
    public void insert(Connection con, User item) throws DaoException {
        super.insert(con, SQL_INSERT_USER, item);
    }

    @Override
    public void remove(Connection con, int id) throws DaoException {
        super.deleteByField(con, SQL_REMOVE_USER, id);
    }

    @Override
    public void update(Connection con, User item) throws DaoException {
        super.updateByField(con, SQL_UPDATE_USER, item, ID_PARAMETER_INDEX, item.getId());
    }

    @Override
    public Optional<User> getByEmail(String email) throws DaoException {
        return super.getByField(SQL_GET_BY_EMAIL, email).stream()
                .filter(Objects::nonNull).findFirst();
    }

    @Override
    protected User mapToEntity(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getInt("users.id"))
                .name(rs.getString("users.name"))
                .surname(rs.getString("users.surname"))
                .email(rs.getString("users.email"))
                .password(rs.getString("users.password"))
                .balance(rs.getBigDecimal("users.balance"))
                .status(UserStatus.resolveUserStatusById(rs.getInt("users.status_id")))
                .cards(new ArrayList<>())
                .roles(new ArrayList<>())
                .build();
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, User entity) throws SQLException {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getSurname());
        ps.setString(3, entity.getEmail());
        ps.setString(4, String.valueOf(entity.getPassword()));
        ps.setBigDecimal(5, entity.getBalance());
        ps.setInt(6, entity.getStatus().getId());
    }

}
