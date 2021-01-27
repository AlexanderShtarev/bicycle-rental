package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.UserRoleDao;
import com.epam.jwd.dao.constant.UserRoleFieldsConstant;
import com.epam.jwd.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserRoleDao extends GenericDao<UserRole> implements UserRoleDao {
    private static MySqlUserRoleDao instance;

    private static final String SQL_ADD_USER_ROLE =
            "";

    private static final String SQL_UPDATE_USER_ROLE =
            "";

    private static final String SQL_DELETE_USER_ROLE =
            "";

    public static MySqlUserRoleDao getInstance() {
        if (instance == null)
            instance = new MySqlUserRoleDao();
        return instance;
    }

    @Override
    protected UserRole mapToEntity(ResultSet rs) throws SQLException {

        return UserRole.resolveRoleById(rs.getLong(UserRoleFieldsConstant.ROLE_ID));

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, UserRole role) throws SQLException {

        ps.setLong(1, role.getId());

    }

}
