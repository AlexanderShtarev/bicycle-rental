package com.epam.jwd.domain;

import java.util.Objects;

public class Inventory extends Entity implements Identified<Long> {
    Store store;
    Product product;

    public Inventory() {
    }

    public Inventory(Inventory.Builder builder) {
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return id.equals(inventory.id) &&
                store.equals(inventory.store) &&
                product.equals(inventory.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, product);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", store=" + store +
                ", product=" + product +
                '}';
    }


    public static Inventory.Builder builder() {
        return new Inventory.Builder();
    }

    public static class Builder {

        Long id;
        Store store;
        Product product;

        private Builder() {
        }

        public Inventory.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Inventory.Builder store(Store store) {
            this.store = store;
            return this;
        }

        public Inventory.Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Inventory build() {
            Inventory inventory = new Inventory(this);
            inventory.id = id;
            inventory.product = product;
            inventory.store = store;
            return inventory;
        }

    }

}
