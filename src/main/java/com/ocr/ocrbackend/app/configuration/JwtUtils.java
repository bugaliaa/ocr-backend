package com.ocr.ocrbackend.app.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JwtUtils {

    public static String generateJwtToken(Map<String, Object> claims, String secretKey) {

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .signWith(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                        SignatureAlgorithm.HS256.getJcaName()))
                .compact();
    }

    public static void validateJwtToken(String authToken, String secretKey) {
        try {
            parseToken(authToken, secretKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static Jws<Claims> parseToken(String authToken, String secretKey) {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName());

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
    }

    public static String getUserNameFromToken(String token, String secretKey) {
        return parseToken(token, secretKey).getBody().getSubject();
    }
}
