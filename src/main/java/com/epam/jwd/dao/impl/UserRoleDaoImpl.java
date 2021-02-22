package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.UserRoleDao;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    private static UserRoleDaoImpl instance = null;

    private static final String SQL_REMOVE_USER_ROLES = "DELETE FROM user_role WHERE user_id = ?";

    private static final String SQL_GET_USER_ROLES =
            "SELECT role.id, role.name FROM role JOIN user_role ur on role.id = ur.role_id\n" +
                    "WHERE ur.user_id = ? ";

    private static final String SQL_INSERT_USER_ROLES =
            "INSERT INTO user_role(user_id, role_id) VALUES(?, ?)";

    public static UserRoleDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserRoleDaoImpl();
        }
        return instance;
    }

    @Override
    public List<UserRole> getUserRoles(int userId) throws DaoException {
        List<UserRole> userRoles = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SQL_GET_USER_ROLES)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserRole userRole = UserRole.resolveUserRoleById(rs.getInt("role.id"));
                userRoles.add(userRole);
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return userRoles;
    }

    @Override
    public void insertAllByUserId(Connection connection, List<UserRole> userRoles, int userId) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER_ROLES)) {
            for (UserRole role : userRoles) {
                ps.setInt(1, userId);
                ps.setInt(2, role.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public void removeUserRoles(Connection con, Integer id) throws DaoException {
        try (PreparedStatement ps = con.prepareStatement(SQL_REMOVE_USER_ROLES)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

}
