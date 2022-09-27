package com.ocr.ocrbackend.app.exception;

public class UnknownUserRoleException extends RuntimeException {
    public UnknownUserRoleException(String role) {
        super("The user role %s is not known to the application.".formatted(role));
    }
}
