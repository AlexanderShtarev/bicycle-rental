package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.ImageDao;
import com.epam.jwd.dao.constant.ImageFieldsConstant;
import com.epam.jwd.domain.Image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlImageDao extends GenericDao<Image> implements ImageDao {
    private static MySqlImageDao instance;

    private static final String SQL_ADD_IMAGE =
            "";

    private static final String SQL_UPDATE_IMAGE =
            "";

    private static final String SQL_DELETE_IMAGE =
            "";

    public static MySqlImageDao getInstance() {
        if (instance == null)
            instance = new MySqlImageDao();
        return instance;
    }

    @Override
    protected Image mapToEntity(ResultSet rs) throws SQLException {

        return Image.builder()
                .id(rs.getLong(ImageFieldsConstant.IMAGE_ID))
                .title(rs.getString(ImageFieldsConstant.IMAGE_TITLE))
                .imageLink(rs.getString(ImageFieldsConstant.IMAGE_IMAGE_LINK))
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Image image) throws SQLException {

        ps.setString(1, image.getTitle());
        ps.setString(2, image.getImageLink());

    }

}
