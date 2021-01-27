package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserRoleDao extends GenericDao<UserRole> {

    private static final String SQL_ADD_USER_ROLE =
            "";

    private static final String SQL_UPDATE_USER_ROLE =
            "";

    private static final String SQL_DELETE_USER_ROLE =
            "";

    @Override
    protected UserRole mapToEntity(ResultSet rs) throws SQLException {

        return UserRole.resolveRoleById(rs.getLong("role.id"));

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, UserRole role) throws SQLException {

        ps.setLong(1, role.getId());

    }

}
