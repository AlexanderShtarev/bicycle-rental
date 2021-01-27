package com.epam.jwd.domain;

import java.util.Objects;

public class ProductType extends Entity {
    Long id;
    String name;

    public ProductType(ProductType.Builder builder) {
    }

    public ProductType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType type = (ProductType) o;
        return id.equals(type.id) &&
                name.equals(type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static ProductType.Builder builder() {
        return new ProductType.Builder();
    }

    public static class Builder {

        private Long id;
        String name;

        private Builder() {
        }

        public ProductType.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductType.Builder name(String name) {
            this.name = name;
            return this;
        }

        public ProductType build() {
            ProductType type = new ProductType(this);
            type.id = id;
            type.name = name;
            return type;
        }

    }

}
