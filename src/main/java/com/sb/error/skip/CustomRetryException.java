package com.sb.error.skip;

public class CustomRetryException extends Exception {
    public CustomRetryException() {
        super();
    }

    public CustomRetryException(String message) {
        super(message);
    }
}
