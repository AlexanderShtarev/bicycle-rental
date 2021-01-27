package com.epam.jwd.dao;

import com.epam.jwd.domain.ProductType;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ProductTypeDao {

    List<ProductType> findAll(Connection con) throws DaoException;

    ProductType findById(Connection con, Integer typeId) throws DaoException;

    ProductType findByName(Connection con, String typeName) throws DaoException;

    void add(Connection con, ProductType type) throws DaoException;

    void update(Connection con, ProductType type) throws DaoException;

    void delete(Connection con, Integer typeId) throws DaoException;

}