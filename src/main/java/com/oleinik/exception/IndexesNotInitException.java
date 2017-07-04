package com.oleinik.exception;

public class IndexesNotInitException extends RuntimeException {

    public IndexesNotInitException() {
    }

    public IndexesNotInitException(String message) {
        super(message);
    }

    public IndexesNotInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexesNotInitException(Throwable cause) {
        super(cause);
    }

    public IndexesNotInitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
