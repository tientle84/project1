package com.revature.exception;

public class ReimbursementNotFoundException extends Exception {
    public ReimbursementNotFoundException() {
        super();
    }

    public ReimbursementNotFoundException(String message) {
        super(message);
    }
}
