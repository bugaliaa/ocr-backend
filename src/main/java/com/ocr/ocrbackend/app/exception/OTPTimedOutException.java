package com.ocr.ocrbackend.app.exception;

public class OTPTimedOutException extends Exception {

    public OTPTimedOutException() {
        super("The OTP you have entered has been expired");
    }
}
