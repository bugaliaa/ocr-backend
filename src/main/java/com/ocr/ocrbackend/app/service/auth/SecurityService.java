package com.ocr.ocrbackend.app.service.auth;

import com.ocr.ocrbackend.app.configuration.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class SecurityService {

    @Value("${app.security.jwt.secret}")
    private String secretKey;

    public String getAuthToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, String.valueOf(userId));

        return JwtUtils.generateJwtToken(claims, secretKey);
    }
}
