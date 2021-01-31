package com.epam.jwd.converter;

import com.epam.jwd.domain.Entity;

public interface Converter<T extends Entity, K> {

    T toEntity(K dto);

    K toDto(T entity);

}
