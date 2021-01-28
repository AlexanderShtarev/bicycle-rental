package com.epam.jwd.domain;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {

    PK getId();

    void setId(PK pk);
}
