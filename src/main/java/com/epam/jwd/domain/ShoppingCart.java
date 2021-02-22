package com.epam.jwd.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ShoppingCart extends Entity {

    private Map<Product, Integer> productMap = new HashMap<>();

}
