package com.epam.jwd.service.impl;

import com.epam.jwd.dao.*;
import com.epam.jwd.service.ProductService;

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



}
