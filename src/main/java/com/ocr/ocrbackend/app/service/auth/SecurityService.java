package com.ocr.ocrbackend.app.service.auth;

import com.ocr.ocrbackend.app.configuration.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecurityService {

    @Value("${app.security.jwt.secret}")
    private String secretKey;

    public String getAuthToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, String.valueOf(userId));

        return JwtUtils.generateJwtToken(claims, secretKey);
    }
}
