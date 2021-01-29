package com.epam.jwd.listener;

import com.epam.jwd.dao.*;
import com.epam.jwd.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitListener implements ServletContextListener {
    DaoFactory daoFactory;
    ProductDao productDao;
    ProductTypeDao typeDao;
    ProductProducerDao producerDao;
    StoreDao storeDao;
    InventoryDao inventoryDao;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConnectionPool.getInstance();

        initCache();
    }

    private void initCache() {
        daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
        productDao = daoFactory.getProductDao();
        typeDao = daoFactory.getProductTypeDao();
        producerDao = daoFactory.getProductProducerDao();
        storeDao = daoFactory.getStoreDao();
        inventoryDao = daoFactory.getInventoryDao();

        productDao.init();
        //todo init other
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ConnectionPool.getInstance().shutdown();
    }
}
