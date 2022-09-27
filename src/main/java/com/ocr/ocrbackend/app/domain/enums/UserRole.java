package com.ocr.ocrbackend.app.domain.enums;

import com.ocr.ocrbackend.app.exception.UnknownUserRoleException;

import java.util.EnumSet;

public enum UserRole {
    USER("USER");
    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public static UserRole getIfPresent(String role) {
        return EnumSet.allOf(UserRole.class)
                .stream()
                .filter(userRole -> userRole.getRole().equals(role))
                .findFirst()
                .orElseThrow(() -> new UnknownUserRoleException(role));
    }

    public String getRole() {
        return role;
    }
}
