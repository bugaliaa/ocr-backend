package com.ocr.ocrbackend.app.web.validation;

import java.time.ZonedDateTime;

public class OTPExpirationValidator {

    private OTPExpirationValidator() {
    }

    public static boolean validateIfOTPHasExpired(ZonedDateTime latestOtpTimeStamp) {
        return (int) ((ZonedDateTime.now().toEpochSecond() - latestOtpTimeStamp.toEpochSecond())) / 60
                > 10;
    }
}
