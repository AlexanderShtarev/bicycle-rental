package com.epam.jwd.domain;

import com.epam.jwd.context.annotation.Column;
import com.epam.jwd.context.annotation.Length;
import com.epam.jwd.context.annotation.NotEmpty;

import java.util.Objects;

public class ProductProducer extends Entity implements Identified<Long> {

    @NotEmpty
    @Column(name = "name")
    @Length(max = 128)
    String name;

    public ProductProducer() {
    }

    private ProductProducer(ProductProducer.Builder builder) {
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
        ProductProducer that = (ProductProducer) o;
        return id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "BicycleManufacturer{" +
                "id=" + id +
                ", manufacturer='" + name + '\'' +
                '}';
    }

    public static ProductProducer.Builder builder() {
        return new ProductProducer.Builder();
    }

    public static class Builder {

        private Long id;
        String name;

        private Builder() {
        }

        public ProductProducer.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductProducer.Builder name(String name) {
            this.name = name;
            return this;
        }

        public ProductProducer build() {
            ProductProducer producer = new ProductProducer(this);
            producer.id = id;
            producer.name = name;
            return producer;
        }

    }

}
