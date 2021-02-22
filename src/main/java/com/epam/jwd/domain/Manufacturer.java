package com.epam.jwd.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Manufacturer extends Entity {

    private String name;

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
