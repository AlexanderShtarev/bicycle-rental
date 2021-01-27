package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.domain.UserStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlUserDao extends GenericDao<User> {

    private static final String SQL_GET_ALL_USERS =
            "";

    private static final String SQL_ADD_USER =
            "";

    private static final String SQL_UPDATE_USER =
            "";

    private static final String SQL_DELETE_USER =
            "";

    @Override
    protected User mapToEntity(ResultSet rs) throws SQLException {

        return User.builder()
                .id(rs.getLong("users.id"))
                .email(rs.getString("users.email"))
                .password(rs.getString("users.password"))
                .name(rs.getString("users.name"))
                .balance(rs.getDouble("users.balance"))
                .status(UserStatus.resolveStatusById(rs.getInt("users.status_id")))
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

}
