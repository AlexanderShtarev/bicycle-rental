package com.epam.jwd.validator.rule;

import com.epam.jwd.context.annotation.Length;
import com.epam.jwd.exception.ValidationException;

public class LengthRule implements Rule<Length> {

    public void check(Length checkLength, String fieldName, Object target) {
        int length = length(target);
        if (length < checkLength.min() || length > checkLength.max()) {
            throw new ValidationException("Invalid string: "
                    + fieldName + ", " + target
                    + " Min length: " + checkLength.min()
                    + " Max length: " + checkLength.max()
                    + " Actual: " + length
            );
        }
    }

    private int length(Object target) {
        return target == null ? 0 : target.toString().length();
    }

    public Class<Length> getAnnotationClass() {
        return Length.class;
    }

}
