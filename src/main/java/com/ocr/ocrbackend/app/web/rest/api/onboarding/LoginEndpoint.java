package com.ocr.ocrbackend.app.web.rest.api.onboarding;

import com.ocr.ocrbackend.app.service.onboarding.LoginService;
import com.ocr.ocrbackend.app.web.rest.dto.error.ApiError;
import com.ocr.ocrbackend.app.web.rest.dto.request.onboarding.VerifyOTPDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ocr.ocrbackend.app.web.rest.api.util.ApiUtils.createErrorResponse;
import static com.ocr.ocrbackend.app.web.rest.api.util.ApiUtils.createResponse;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginEndpoint {

    @NonNull
    private final LoginService loginService;

    @GetMapping("send-otp")
    public ResponseEntity<?> sendOTP(@RequestParam("contactNumber") String contactNumber,
                                     @RequestParam("countryCode") int countryCode) {
        try {
            loginService.sendOTP(contactNumber, countryCode);
            return ResponseEntity.ok(createResponse(null));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            Map<String, ApiError> errorResponse = createErrorResponse(apiError);

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestBody VerifyOTPDTO verifyOTPDTO) {
        try {
            return ResponseEntity.ok(createResponse(loginService.verifyOTP(verifyOTPDTO)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            Map<String, ApiError> errorResponse = createErrorResponse(apiError);

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
