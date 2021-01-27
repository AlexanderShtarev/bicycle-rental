package com.epam.jwd.dao;

import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ProductProducerDao {

    List<ProductProducer> findAll(Connection con) throws DaoException;

    ProductProducer findById(Connection con, Integer producerId) throws DaoException;

    ProductProducer findByName(Connection con, String producerName) throws DaoException;

    void add(Connection con, ProductProducer producer) throws DaoException;

    void update(Connection con, ProductProducer producer) throws DaoException;

    void delete(Connection con, Integer producerId) throws DaoException;

}