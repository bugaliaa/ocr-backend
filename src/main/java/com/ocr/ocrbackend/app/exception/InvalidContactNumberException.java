package com.ocr.ocrbackend.app.exception;

public class InvalidContactNumberException extends Exception {

    public InvalidContactNumberException(String contactNumber) {
        super("The contact number %s is not valid".formatted(contactNumber));
    }
}
