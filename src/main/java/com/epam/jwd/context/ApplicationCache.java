package com.epam.jwd.context;

import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.Store;
import com.epam.jwd.domain.User;

import java.util.List;

public class ApplicationCache {
    public static final ApplicationCache APPLICATION_CACHE = new ApplicationCache();

    private ApplicationCache() {
    }

    private List<Product> products;

    private List<User> users;

    private List<Rental> rentals;

    private List<Store> stores;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

}
