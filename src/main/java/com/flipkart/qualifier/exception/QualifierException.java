package com.flipkart.qualifier.exception;

/**
 * User: smriti.a
 * Date: 25/03/17
 * Time: 4:41 PM
 */

public class QualifierException extends Exception {

    public QualifierException(String message) {
        super(message);
    }

    public QualifierException(String message, Throwable cause) {
        super(message, cause);
    }

    public QualifierException(Throwable cause) {
        super(cause);
    }

}

