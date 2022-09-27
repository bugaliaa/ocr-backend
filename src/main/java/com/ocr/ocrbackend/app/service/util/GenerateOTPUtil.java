package com.ocr.ocrbackend.app.service.util;

import java.security.SecureRandom;
import java.util.stream.IntStream;

public class GenerateOTPUtil {

    private GenerateOTPUtil() {
    }

    public static String generateSecureOTP() {
        String nextLong = String.valueOf(new SecureRandom().nextLong(999999));
        int numberOfDigits = nextLong.length();
        StringBuilder otp = new StringBuilder(nextLong);

        if (numberOfDigits < 6) {
            IntStream.range(0, 6 - numberOfDigits).forEach(i -> otp.insert(0, "0"));
        }

        return otp.toString();
    }
}
