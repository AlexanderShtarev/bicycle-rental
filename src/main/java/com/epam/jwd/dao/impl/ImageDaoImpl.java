package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.ImageDao;
import com.epam.jwd.domain.Image;
import com.epam.jwd.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ImageDaoImpl extends GenericDao<Image> implements ImageDao {

    @Override
    protected Image parseResultSet(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Image object) throws DaoException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Image object) throws DaoException {

    }

    @Override
    public Image findImageByProductId() {
        return null;
    }

}
