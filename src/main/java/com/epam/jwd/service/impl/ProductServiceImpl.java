package com.epam.jwd.service.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.ProductCriteria;
import com.epam.jwd.dao.*;
import com.epam.jwd.domain.Product;
import com.epam.jwd.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    TransactionHandler transactionHandler;

    ProductDao productDao;

    ProductProducerDao producerDao;

    ProductTypeDao typeDao;

    private static ProductServiceImpl instance;

    private ProductServiceImpl() {
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
            productDao = daoFactory.getProductDao();
            producerDao = daoFactory.getProductProducerDao();
            typeDao = daoFactory.getProductTypeDao();
            transactionHandler = new TransactionHandler();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Product> getFeaturedProducts() {
        return transactionHandler.transactional(con ->
                productDao.getAll(con));
    }

    @Override
    public List<Product> getProductsByCriteria(Criteria<? extends Product> criteria) {
        return transactionHandler.transactional(con ->
                productDao.getByCriteria(con, criteria));
    }

    @Override
    public Product getProductById(Long id) {
        return transactionHandler.transactional(con -> {
            Product product = null;
            ProductCriteria criteria = ProductCriteria.builder().id(id).build();
            List<Product> products = productDao.getByCriteria(con, criteria);
            if (products.size() > 0) {
                product = products.get(0);
            }
            return product;
        });
    }

}
