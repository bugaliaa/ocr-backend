package com.ocr.ocrbackend.app.web.rest.dto.response.auth;

import com.ocr.ocrbackend.app.domain.enums.Country;
import com.ocr.ocrbackend.app.domain.enums.UserRole;
import com.ocr.ocrbackend.app.domain.user.User;
import com.ocr.ocrbackend.app.web.rest.dto.response.onboarding.ResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDTO implements ResponseDTO {

    private final Long id;
    private final String name;
    private final Country country;
    private final String contactNumber;
    private final String email;
    private final UserRole role;
    private final String authToken;

    public static LoginDTO of(User user, String authToken) {
        return LoginDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .country(user.getCountry())
                .contactNumber(user.getContactNumber())
                .email(user.getEmail())
                .role(user.getRole())
                .authToken(authToken)
                .build();
    }
}
