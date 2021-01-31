package com.epam.jwd.domain;

import com.epam.jwd.context.annotation.Column;
import com.epam.jwd.context.annotation.Length;
import com.epam.jwd.context.annotation.NotEmpty;

import java.util.Objects;

public class Product extends Entity implements Identified<Long> {

    @NotEmpty
    ProductProducer producer;

    @NotEmpty
    @Column(name = "model")
    @Length(min = 1, max = 128)
    String model;

    @NotEmpty
    ProductType type;

    @NotEmpty
    Image image;

    @NotEmpty
    @Column(name = "price_per_hour")
    Double pricePerHour;

    public Product() {
    }

    public Product(Product.Builder builder) {
    }

    public ProductProducer getProducer() {
        return producer;
    }

    public void setProducer(ProductProducer producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product bicycle = (Product) o;
        return Double.compare(bicycle.pricePerHour, pricePerHour) == 0 &&
                id.equals(bicycle.id) &&
                producer.equals(bicycle.producer) &&
                model.equals(bicycle.model) &&
                type.equals(bicycle.type) &&
                image.equals(bicycle.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producer, model, type, image, pricePerHour);
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "id=" + id +
                ", producer=" + producer +
                ", model=" + model +
                ", type=" + type +
                ", image=" + image +
                ", pricePerHour=" + pricePerHour +
                '}';
    }


    public static Product.Builder builder() {
        return new Product.Builder();
    }

    public static class Builder {
        Long id;
        ProductProducer producer;
        String model;
        ProductType type;
        Image image;
        Double pricePerHour;

        private Builder() {
        }

        public Product.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Product.Builder producer(ProductProducer producer) {
            this.producer = producer;
            return this;
        }

        public Product.Builder model(String model) {
            this.model = model;
            return this;
        }

        public Product.Builder type(ProductType type) {
            this.type = type;
            return this;
        }

        public Product.Builder image(Image image) {
            this.image = image;
            return this;
        }

        public Product.Builder pricePerHour(Double pricePerHour) {
            this.pricePerHour = pricePerHour;
            return this;
        }

        public Product build() {
            Product product = new Product(this);
            product.id = id;
            product.image = image;
            product.model = model;
            product.pricePerHour = pricePerHour;
            product.producer = producer;
            product.type = type;
            return product;
        }
    }

}
