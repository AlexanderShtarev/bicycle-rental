package com.epam.jwd.validator.rule;

import com.epam.jwd.context.annotation.Pattern;
import com.epam.jwd.exception.ValidationException;

public class PatternRule implements Rule<Pattern> {

    @Override
    public void check(Pattern annotation, String fieldName, Object target) throws ValidationException {
        java.util.regex.Pattern valid_regex = java.util.regex.Pattern
                .compile(annotation.pattern(),
                        java.util.regex.Pattern.CASE_INSENSITIVE);
        String input = (String) target;

        if (valid_regex.matcher(input).find()) {
            throw new ValidationException("Wrong input value");
        }
    }

    @Override
    public Class<Pattern> getAnnotationClass() {
        return Pattern.class;
    }

}
