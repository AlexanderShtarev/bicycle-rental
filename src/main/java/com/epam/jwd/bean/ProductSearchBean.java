package com.epam.jwd.bean;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductSearchBean {

    private String name;
    private List<Integer> categories;
    private List<Integer> manufacturers;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private String sortedBy;
    private boolean isDescending;
    private Integer limit;
    private Integer offset;
    private Integer page;

}
