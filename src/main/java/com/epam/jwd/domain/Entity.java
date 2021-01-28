package com.epam.jwd.domain;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable, Identified<Long> {

    Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
