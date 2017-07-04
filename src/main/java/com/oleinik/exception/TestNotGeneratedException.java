package com.oleinik.exception;

public class TestNotGeneratedException extends RuntimeException {

    public TestNotGeneratedException() {
    }

    public TestNotGeneratedException(String message) {
        super(message);
    }

    public TestNotGeneratedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestNotGeneratedException(Throwable cause) {
        super(cause);
    }

    public TestNotGeneratedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
