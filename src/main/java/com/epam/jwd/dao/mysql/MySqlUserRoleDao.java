package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.AbstractJDBCDao;
import com.epam.jwd.dao.UserRoleDao;
import com.epam.jwd.dao.constant.UserRoleFieldsConstant;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlUserRoleDao extends AbstractJDBCDao<UserRole, Integer> implements UserRoleDao {
    private static MySqlUserRoleDao instance;

    private static final String SQL_SELECT_USER_ROLE =
            "";

    private static final String SQL_CREATE_USER_ROLE =
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
    public String getSelectQuery() {
        return SQL_SELECT_USER_ROLE;
    }

    @Override
    public String getCreateQuery() {
        return SQL_CREATE_USER_ROLE;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_USER_ROLE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_USER_ROLE;
    }

    @Override
    protected List<UserRole> parseResultSet(ResultSet rs) throws DaoException {
        List<UserRole> list = new LinkedList<>();
        try {
            while (rs.next()) {
                UserRole role = UserRole.resolveRoleById(rs.getInt(UserRoleFieldsConstant.ROLE_ID));
                list.add(role);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, UserRole role) throws DaoException {
        try {
            ps.setString(1, role.getName());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, UserRole role) throws DaoException {
        try {
            ps.setString(1, role.getName());
            ps.setLong(2, role.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
