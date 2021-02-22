package com.epam.jwd.exception;

/**
 * Checked exception that thrown if application can't be initialized.
 *
 * @see Error
 */
public class InitializingException extends Error {
    public InitializingException() {
    }

    public InitializingException(String message) {
        super(message);
    }

    public InitializingException(Throwable cause) {
        super(cause);
    }

    public InitializingException(String message, Throwable cause) {
        super(message, cause);
    }

}
