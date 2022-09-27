package com.ocr.ocrbackend.app.domain.user;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "user_otp")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(UserOTPId.class)
public class UserOTP {

    @Id
    @Column(name = "contact_number", nullable = false, updatable = false)
    private String contactNumber;

    @Id
    @Column(name = "country_code", nullable = false, updatable = false)
    private int countryCode;

    @Column(name = "latest_otp", length = 6)
    private String latestOtp;

    @Column(name = "latest_otp_timestamp")
    private ZonedDateTime latestOtpTimestamp;
}
