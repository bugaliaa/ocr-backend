package com.ocr.ocrbackend.app.exception;

public class InvalidOTPException extends Exception {

    public InvalidOTPException() {
        super("The OTP you have entered is invalid.");
    }
}