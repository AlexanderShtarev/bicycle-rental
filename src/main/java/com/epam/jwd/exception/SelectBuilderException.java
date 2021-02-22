package com.epam.jwd.exception;

import com.epam.jwd.dao.builder.SelectQueryBuilder;

/**
 * Checked exception that thrown from QueryBuilder.
 *
 * @see SelectQueryBuilder
 * @see Exception
 */
public class SelectBuilderException extends Exception {

    public SelectBuilderException() {
    }

    public SelectBuilderException(String message) {
        super(message);
    }

    public SelectBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectBuilderException(Throwable cause) {
        super(cause);
    }

}
