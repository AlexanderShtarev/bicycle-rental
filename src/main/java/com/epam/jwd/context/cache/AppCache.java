package com.epam.jwd.context.cache;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.dao.*;
import com.epam.jwd.domain.*;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class AppCache implements AppContext {
    public static final AppCache APPLICATION_CACHE = new AppCache();

    private AppCache() {
    }

    private Collection<Image> images = new CopyOnWriteArrayList<>();

    private Collection<Inventory> inventories = new CopyOnWriteArrayList<>();

    private Collection<Product> products = new CopyOnWriteArrayList<>();

    private Collection<ProductProducer> productProducers = new CopyOnWriteArrayList<>();

    private Collection<ProductType> productTypes = new CopyOnWriteArrayList<>();

    private Collection<Rental> rentals = new CopyOnWriteArrayList<>();

    private Collection<Store> stores = new CopyOnWriteArrayList<>();

    private Collection<User> users = new CopyOnWriteArrayList<>();

    @Override
    public <T extends Entity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        Collection<T> entities = new CopyOnWriteArrayList<>();
        if (tClass.equals(Image.class)) {
            entities = (Collection<T>) images;
        }
        if (tClass.equals(Inventory.class)) {
            entities = (Collection<T>) inventories;
        }
        if (tClass.equals(Product.class)) {
            entities = (Collection<T>) products;
        }
        if (tClass.equals(ProductProducer.class)) {
            entities = (Collection<T>) productProducers;
        }
        if (tClass.equals(ProductType.class)) {
            entities = (Collection<T>) productTypes;
        }
        if (tClass.equals(Rental.class)) {
            entities = (Collection<T>) rentals;
        }
        if (tClass.equals(Store.class)) {
            entities = (Collection<T>) stores;
        }
        if (tClass.equals(User.class)) {
            entities = (Collection<T>) users;
        }
        return entities;
    }

    @Override
    public void init() {

        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);

        InventoryDao inventoryDao = daoFactory.getInventoryDao();

        ProductDao productDao = daoFactory.getProductDao();

        ProductProducerDao productProducerDao = daoFactory.getProductProducerDao();

        ProductTypeDao productTypeDao = daoFactory.getProductTypeDao();

        RentalDao rentalDao = daoFactory.getRentalDao();

        StoreDao storeDao = daoFactory.getStoreDao();

        UserDao userDao = daoFactory.getUserDao();

        TransactionHandler transactionHandler = new TransactionHandler();
        transactionHandler.transactional(con -> {
            //todo inventories = in
            products = productDao.getAll(con);
            productProducers = productProducerDao.getAll(con);
            productTypes = productTypeDao.getAll(con);
            rentals = rentalDao.getAll(con);
            //todo stores = storeDao
            users = userDao.getAll(con);
            return true;
        });

    }

}
