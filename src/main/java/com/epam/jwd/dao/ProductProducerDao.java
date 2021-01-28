package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ProductProducerDao {

    List<ProductProducer> getAll(Connection con) throws DaoException;

    List<ProductProducer> getByCriteria(Connection con, Criteria<? extends ProductProducer> criteria) throws DaoException;

    Long add(Connection con, ProductProducer producer) throws DaoException;

    void update(Connection con, ProductProducer producer) throws DaoException;

    void delete(Connection con, Long producerId) throws DaoException;

}