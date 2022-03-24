package com.revature.exception;

public class FailedRegisterException extends Exception {
    public FailedRegisterException() {
        super();
    }

    public FailedRegisterException(String message) {
        super(message);
    }
}
