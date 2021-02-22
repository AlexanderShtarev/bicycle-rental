package com.epam.jwd.exception;

/**
 *  Unchecked Exception that thrown if Entity does not exist.
 *
 * @see com.epam.jwd.domain.UserStatus
 * @see com.epam.jwd.domain.UserRole
 * @see com.epam.jwd.domain.RentalStatus
 * @see RuntimeException
 */
public class UnknownEntityException extends RuntimeException{

    public UnknownEntityException() {
    }

    public UnknownEntityException(String message) {
        super(message);
    }

    public UnknownEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownEntityException(Throwable cause) {
        super(cause);
    }

}
