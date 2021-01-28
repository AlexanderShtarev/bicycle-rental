package com.epam.jwd.domain;

import java.util.Objects;

public class Store extends Entity implements Identified<Long> {
    Long id;
    String address;
    String phone;

    public Store() {
    }

    public Store(Store.Builder builder) {

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return id.equals(store.id) &&
                address.equals(store.address) &&
                phone.equals(store.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, phone);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static Store.Builder builder() {
        return new Store.Builder();
    }

    public static class Builder {

        Long id;
        String address;
        String phone;

        private Builder() {
        }

        public Store.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Store.Builder address(String address) {
            this.address = address;
            return this;
        }

        public Store.Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Store build() {
            Store store = new Store(this);
            store.id = id;
            store.address = address;
            store.phone = phone;
            return store;
        }

    }

}
