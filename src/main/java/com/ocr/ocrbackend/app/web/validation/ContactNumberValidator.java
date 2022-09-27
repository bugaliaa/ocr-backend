package com.ocr.ocrbackend.app.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactNumberValidator {

    private ContactNumberValidator() {
    }

    public static boolean validateContactNumber(String contactNumber) {
        //TODO: This validation can be more strict.
        Pattern pattern = Pattern.compile("^[6789]\\d{9}$");
        Matcher matcher = pattern.matcher(contactNumber);

        return matcher.matches();
    }
}
