package com.ocr.ocrbackend.app.web.rest.api.util;

import com.ocr.ocrbackend.app.web.rest.dto.error.ApiError;
import com.ocr.ocrbackend.app.web.rest.dto.response.onboarding.ResponseDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ApiUtils {

    private ApiUtils() {
    }

    public static Map<String, ApiError> createErrorResponse(ApiError apiError) {
        Map<String, ApiError> errorResponse = new HashMap<>();
        errorResponse.put("error", apiError);

        return errorResponse;
    }

    public static Map<String, Object> createResponse(ResponseDTO responseDTO) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("data", responseDTO);

        return successResponse;
    }
}
