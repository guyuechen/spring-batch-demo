package com.sb.error.skiplistener;

public class CustomRetryException extends Exception {
    public CustomRetryException() {
        super();
    }

    public CustomRetryException(String message) {
        super(message);
    }
}
