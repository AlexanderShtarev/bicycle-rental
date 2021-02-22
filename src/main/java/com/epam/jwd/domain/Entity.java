package com.epam.jwd.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public abstract class Entity implements BaseEntity, Serializable, Cloneable {

    private Integer id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

}
