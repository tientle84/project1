package com.revature.exception;

public class FailedLoginException extends Exception {
    public FailedLoginException() {
        super();
    }

    public FailedLoginException(String message) {
        super(message);
    }
}
