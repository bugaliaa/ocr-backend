package com.ocr.ocrbackend.app.domain.enums;

import com.ocr.ocrbackend.app.exception.InvalidCountryCodeException;

import java.util.Arrays;

public enum Country {
    INDIA(91, "IN", "IND");

    private final int code;
    private final String alpha2;
    private final String alpha3;

    Country(int code, String alpha2, String alpha3) {
        this.code = code;
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
    }

    public static Country getIfPresent(int dial) throws InvalidCountryCodeException {
        return Arrays.stream(Country.values())
                .filter(code -> code.code == dial)
                .findFirst()
                .orElseThrow(() -> new InvalidCountryCodeException(dial));
    }

    public int getCode() {
        return code;
    }
}
