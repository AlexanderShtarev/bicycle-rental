package com.epam.jwd.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class RentalItem extends Entity {

    private final Product product;

    private final BigDecimal price;

    private final Integer count;

}
