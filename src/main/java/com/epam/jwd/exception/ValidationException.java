package com.epam.jwd.exception;

/**
 * Unchecked Exception that thrown if Entity does not exist.
 *
 * @see com.epam.jwd.validator.EntityValidator
 * @see RuntimeException
 */
public class ValidationException extends RuntimeException {

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

}
