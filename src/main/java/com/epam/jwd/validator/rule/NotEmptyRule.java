package com.epam.jwd.validator.rule;


import com.epam.jwd.context.annotation.NotEmpty;
import com.epam.jwd.exception.ValidationException;

public class NotEmptyRule implements Rule<NotEmpty> {

    public void check(NotEmpty annotation, String fieldName, Object target) {
        if (target == null || (target instanceof String && target.toString().isEmpty())) {
            throw new ValidationException("Empty field: " + fieldName);
        }
    }

    public Class<NotEmpty> getAnnotationClass() {
        return NotEmpty.class;
    }

}