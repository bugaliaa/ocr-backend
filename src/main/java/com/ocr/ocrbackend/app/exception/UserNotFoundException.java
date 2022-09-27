package com.ocr.ocrbackend.app.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("A user with ID %d does not exist.".formatted(id));
    }

    public UserNotFoundException(String referralCode) {
        super("A user with provided referral code %s does not exist.".formatted(referralCode));
    }
}
