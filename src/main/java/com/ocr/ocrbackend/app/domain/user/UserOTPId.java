package com.ocr.ocrbackend.app.domain.user;

import java.io.Serializable;
import java.util.Objects;

public class UserOTPId implements Serializable {

    private String contactNumber;
    private int countryCode;

    public UserOTPId() {
    }

    public UserOTPId(String contactNumber, int countryCode) {
        this.contactNumber = contactNumber;
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserOTPId userOTPId = (UserOTPId) o;

        return Objects.equals(contactNumber, userOTPId.contactNumber)
                && countryCode == userOTPId.countryCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactNumber, countryCode);
    }
}
