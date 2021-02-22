package com.epam.jwd.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@Getter
@SuperBuilder
@EqualsAndHashCode
public class Token extends Entity {

    private String token;

    private Date createdDate;

    private User user;

    public Token(User user) {
        this.user = user;
        createdDate = new Date();
        token = UUID.randomUUID().toString();
    }

}
