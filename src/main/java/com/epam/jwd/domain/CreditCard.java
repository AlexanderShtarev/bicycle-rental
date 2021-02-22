package com.epam.jwd.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreditCard extends Entity {

    private final String owner;

    private final String number;

    private final String CVV;

    private final Date expiration;

    public CreditCard(int id, String owner, String number, String CVV, Date expiration) {
        super(id);
        this.owner = owner;
        this.number = number;
        this.CVV = CVV;
        this.expiration = expiration;
    }

}
