package com.epam.jwd.context;

import com.epam.jwd.dao.DaoFactory;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.validator.EntityValidator;

import java.util.Collection;

public interface Context {

    <T extends Entity> Collection<T> getCache(Class<T> tClass);

    <T extends Entity> void addToCache(T entity);

    <T extends Entity> void removeFromCache(T entity);

    ServiceFactory getServiceFactory();

    DaoFactory getDaoFactory();

    EntityValidator getEntityValidator();

    void init();

}
