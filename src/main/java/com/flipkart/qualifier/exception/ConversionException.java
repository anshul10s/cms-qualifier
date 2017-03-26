package com.flipkart.qualifier.exception;

/**
 * User: smriti.a
 * Date: 24/03/17
 * Time: 8:32 AM
 */

public class ConversionException extends Exception {

    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionException(Throwable cause) {
        super(cause);
    }

}
