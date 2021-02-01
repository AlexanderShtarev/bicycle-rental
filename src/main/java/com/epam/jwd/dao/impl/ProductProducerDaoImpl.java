package com.epam.jwd.dao.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.ProductProducerDao;
import com.epam.jwd.dao.constant.ProductProducerFieldsConstant;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductProducerDaoImpl extends GenericDao<ProductProducer> implements ProductProducerDao {
    private static ProductProducerDao instance;

    private static final String SQL_SELECT_ALL_PRODUCERS =
            "SELECT producer.id, producer.name FROM producer\n";

    private static final String SQL_CREATE_PRODUCER =
            "INSERT INTO producer(name)\n" +
                    "VALUES (?);";

    private static final String SQL_UPDATE_PRODUCER =
            "UPDATE producer\n" +
                    "SET producer = ?\n" +
                    "WHERE id = ?";

    private static final String SQL_DELETE_PRODUCER =
            "DELETE producer\n" +
                    "FROM producer\n" +
                    "WHERE id = ?";

    public static ProductProducerDao getInstance() {
        if (instance == null)
            instance = new ProductProducerDaoImpl();
        return instance;
    }

    @Override
    protected ProductProducer parseResultSet(ResultSet rs) throws DaoException, SQLException {
       return ProductProducer.builder()
                        .id(rs.getLong(ProductProducerFieldsConstant.PRODUCER_ID))
                        .name(rs.getString(ProductProducerFieldsConstant.PRODUCER_NAME))
                        .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, ProductProducer producer) throws DaoException, SQLException {
            ps.setString(1, producer.getName());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, ProductProducer producer) throws DaoException, SQLException {
            ps.setString(1, producer.getName());
            ps.setLong(2, producer.getId());
    }

    @Override
    public Optional<ProductProducer> findSingleProducerByCriteria(Criteria<? extends ProductProducer> criteria) {
        return Optional.empty();
    }

    @Override
    public ProductProducer findProducerByProductId() {
        return null;
    }

    @Override
    public ProductProducer persist(Connection con, ProductProducer producer) {
        return null;
    }
}
