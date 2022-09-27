package com.ocr.ocrbackend.app.exception;

public class InvalidCountryCodeException extends Exception{
    public InvalidCountryCodeException(int dial) {
        super("The country code %d is not a valid dial code.".formatted(dial));
    }
}
