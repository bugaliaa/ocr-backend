package com.ocr.ocrbackend.app.web.rest.dto.request.onboarding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOTPDTO {

    private String contactNumber;
    private int countryCode;
    private String otp;
    private String referralCode;
}
