package com.sb.demo.error.retry;

public class CustomRetryException extends Exception {
    public CustomRetryException() {
        super();
    }

    public CustomRetryException(String message) {
        super(message);
    }
}
