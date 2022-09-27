package com.ocr.ocrbackend.app.repository.onboarding;

import com.ocr.ocrbackend.app.domain.user.UserOTP;
import com.ocr.ocrbackend.app.domain.user.UserOTPId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOTPRepository extends JpaRepository<UserOTP, UserOTPId> {
}
