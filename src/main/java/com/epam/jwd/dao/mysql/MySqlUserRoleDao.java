package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.UserRoleDao;
import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlUserRoleDao implements UserRoleDao {

    public static MySqlUserRoleDao instance;
    TransactionHandler transactionHandler;

    private static final String SQL_GET_ROLE_BY_USER_ID =
            "SELECT (id, name) FROM role" +
                    " JOIN user_role ur on role.id = ur.role_id" +
                    " WHERE user_id = ?";

    private static final String SQL_DELETE_USER_ROLES =
            "DELETE user_role FROM user_role " +
                    "WHERE user_id = ?";

    private static final String SQL_INSERT_USER_ROLES =
            "INSERT INTO user_role(user_id, role_id) " +
                    "VALUES (?, ?)";

    public static UserRoleDao getInstance() {
        if (instance == null)
            instance = new MySqlUserRoleDao();
        return instance;
    }

    @Override
    public List<UserRole> getRolesByUserId(Long id) throws DaoException {
        return transactionHandler.transactional(con -> {
            List<UserRole> list = new LinkedList<>();
            PreparedStatement statement = con.prepareStatement(SQL_GET_ROLE_BY_USER_ID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserRole role = UserRole.resolveRoleById(rs.getInt("id"));
                list.add(role);
            }
            return list;
        });
    }

    @Override
    public boolean updateUserRole(User user) throws DaoException {
        return transactionHandler.transactional(con -> {
            List<UserRole> roles = user.getRoles();
            PreparedStatement statement = con.prepareStatement(SQL_DELETE_USER_ROLES);
            ResultSet rs = statement.executeQuery();
            for (UserRole role : roles) {
                statement = con.prepareStatement(SQL_INSERT_USER_ROLES);
                statement.setLong(1, user.getId());
                statement.setInt(2, role.getId());
                statement.executeUpdate();
            }
            return true;
        });
    }

}