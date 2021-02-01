package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.ProductType;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProductTypeDao {

    Optional<ProductType> findSingleTypeByCriteria(Criteria<? extends ProductType> criteria);

    ProductType findTypeByProductId();

    List<ProductType> findAllTypesByCriteria(Criteria<? extends ProductType> criteria);

    ProductType persist(Connection con, ProductType type);
}
