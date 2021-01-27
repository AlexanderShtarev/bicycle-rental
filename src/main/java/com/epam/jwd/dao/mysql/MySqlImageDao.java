package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.Image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlImageDao extends GenericDao<Image> {

    private static final String SQL_ADD_IMAGE =
            "";

    private static final String SQL_UPDATE_IMAGE =
            "";

    private static final String SQL_DELETE_IMAGE =
            "";

    @Override
    protected Image mapToEntity(ResultSet rs) throws SQLException {

        return Image.builder()
                .id(rs.getLong("images.id"))
                .title(rs.getString("images.title"))
                .imageLink(rs.getString("images.image_link"))
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Image image) throws SQLException {

        ps.setString(1, image.getTitle());
        ps.setString(2, image.getImageLink());

    }

}
