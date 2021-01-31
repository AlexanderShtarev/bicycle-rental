package com.epam.jwd.context;

import com.epam.jwd.domain.Entity;

import java.util.Collection;

public interface AppContext {

    <T extends Entity> Collection<T> retrieveBaseEntityList(Class<T> tClass);

    void init();

}
