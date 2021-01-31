package com.epam.jwd.context;

import com.epam.jwd.context.cache.AppCache;

public class AppProperties {

    public void init() {
        AppCache.APPLICATION_CACHE.init();
    }

}
