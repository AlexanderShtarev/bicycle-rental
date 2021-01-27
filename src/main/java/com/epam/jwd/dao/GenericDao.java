package com.epam.jwd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericDao<T> {

    protected abstract T mapToEntity(ResultSet rs) throws SQLException;

    protected abstract void mapFromEntity(PreparedStatement ps, T entity) throws SQLException;

}
