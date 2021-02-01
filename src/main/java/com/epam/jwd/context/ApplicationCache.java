package com.epam.jwd.context;

import com.epam.jwd.dao.*;
import com.epam.jwd.domain.*;
import com.epam.jwd.service.ServiceFactory;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class ApplicationCache implements Cache {
    public static final Cache CACHE = new ApplicationCache();

    private Collection<Inventory> inventoryCache;

    private Collection<Product> productCache;

    private Collection<ProductProducer> productProducerCache;

    private Collection<ProductType> productTypeCache;

    private Collection<Rental> rentalCache;

    private Collection<Store> storeCache;

    private Collection<User> userCache;

    @Override
    public <T extends Entity> Collection<T> getCache(Class<T> tClass) {
        Collection<T> cache = new CopyOnWriteArrayList<>();
        System.out.println("from cache");
        return this.defineClass(tClass);
    }

    @Override
    public <T extends Entity> void add(T entity) {
        this.defineClass(entity.getClass()).add(entity);
    }

    @Override
    public <T extends Entity> void remove(T entity) {
        this.defineClass(entity.getClass()).remove(entity);
    }

    @Override
    public void initCache() {
        //inventoryCache.init(inventoryDao.getAll(con));
        productCache = new CopyOnWriteArrayList<>();
        productProducerCache = new CopyOnWriteArrayList<>();
        productTypeCache = new CopyOnWriteArrayList<>();
        //rentalCache = daoFactory.getRentalDao().findAll();
        //storeCache.init(storeDao.getAll(con));
        //userCache = daoFactory.getUserDao().findAll();
    }

    @Override
    public void clean() {

    }

    @Override
    public void clear() {

    }

    private <T extends Entity> Collection<T> defineClass(Class<? extends Entity> tClass) {
        Collection<T> entities = new CopyOnWriteArrayList<>();

        if (tClass.equals(Inventory.class)) {
            entities = (Collection<T>) inventoryCache;
        }
        if (tClass.equals(Product.class)) {
            entities = (Collection<T>) productCache;
        }
        if (tClass.equals(ProductProducer.class)) {
            entities = (Collection<T>) productProducerCache;
        }
        if (tClass.equals(ProductType.class)) {
            entities = (Collection<T>) productTypeCache;
        }
        if (tClass.equals(Rental.class)) {
            entities = (Collection<T>) rentalCache;
        }
        if (tClass.equals(Store.class)) {
            entities = (Collection<T>) storeCache;
        }
        if (tClass.equals(User.class)) {
            entities = (Collection<T>) userCache;
        }
        System.out.println(entities);
        return entities;
    }

}
