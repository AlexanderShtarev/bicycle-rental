package com.epam.jwd.context;

import com.epam.jwd.dao.*;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.pool.ConnectionPool;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.validator.AnnotationValidator;
import com.epam.jwd.validator.EntityValidator;
import com.epam.jwd.validator.rule.LengthRule;
import com.epam.jwd.validator.rule.NotEmptyRule;
import com.epam.jwd.validator.rule.PatternRule;

import java.util.ArrayList;
import java.util.Collection;

public class ApplicationContext implements Context {
    public static final Context APPLICATION_CONTEXT = new ApplicationContext();

    private EntityValidator entityValidator;

    @Override
    public <T extends Entity> Collection<T> getCache(Class<T> tClass) {
        return  ApplicationCache.CACHE.getCache(tClass);
    }

    @Override
    public <T extends Entity> void addToCache(T entity) {
        ApplicationCache.CACHE.add(entity);
    }

    @Override
    public <T extends Entity> void removeFromCache(T entity) {
        ApplicationCache.CACHE.remove(entity);
    }

    @Override
    public ServiceFactory getServiceFactory() {
        return ServiceFactory.getInstance();
    }

    @Override
    public DaoFactory getDaoFactory() {
        return DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
    }

    @Override
    public EntityValidator getEntityValidator() {
        return this.entityValidator;
    }

    @Override
    public void init() {
        ConnectionPool.getInstance();

        ApplicationCache.CACHE.initCache();

        initValidator();

    }

    private void initValidator() {
        NotEmptyRule notEmptyRule = new NotEmptyRule();
        LengthRule lengthRule = new LengthRule();
        PatternRule patternRule = new PatternRule();

        entityValidator = new AnnotationValidator(new ArrayList<>() {{
            add(notEmptyRule);
            add(lengthRule);
            add(patternRule);
        }});
    }

}
