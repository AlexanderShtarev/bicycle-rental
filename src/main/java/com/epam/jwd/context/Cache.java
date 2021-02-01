package com.epam.jwd.context;

import com.epam.jwd.domain.*;

import java.util.Collection;

public interface Cache {
    public <T extends Entity> Collection<T> getCache(Class<T> tClass);

    public <T extends Entity> void add(T entity);

    public <T extends Entity> void remove(T entity);

    void initCache();

    void clean();

    void clear();

}
