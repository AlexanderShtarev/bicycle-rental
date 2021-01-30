package com.epam.jwd.validator.rule;

import com.epam.jwd.exception.ValidationException;

import java.lang.annotation.Annotation;

public interface Rule<T extends Annotation> {

    void check(T annotation, String fieldName, Object target) throws ValidationException;

    Class<T> getAnnotationClass();

}
