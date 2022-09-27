package com.ocr.ocrbackend.app.exception;

public class UserOTPNotFoundException extends Exception {

    public UserOTPNotFoundException(String contactNumber, int countryCode) {
        super("The user with contact number %s and country code %d does not have an OTP generated yet.".formatted(
                contactNumber, countryCode));
    }
}
