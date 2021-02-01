package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.ProductProducer;

import java.sql.Connection;
import java.util.Optional;

public interface ProductProducerDao {

    Optional<ProductProducer> findSingleProducerByCriteria(Criteria<? extends ProductProducer> criteria);

    ProductProducer findProducerByProductId();

    ProductProducer persist(Connection con, ProductProducer producer);

}
