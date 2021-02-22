package com.epam.jwd.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Product extends Entity {

    private Category category;

    private Manufacturer manufacturer;

    private String name;

    private BigDecimal price;

    private String description;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + getId() +
                ", category=" + category +
                ", manufacturer=" + manufacturer +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}
