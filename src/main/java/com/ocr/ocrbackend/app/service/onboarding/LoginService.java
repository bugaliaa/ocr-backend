package com.ocr.ocrbackend.app.service.onboarding;

import com.ocr.ocrbackend.app.domain.enums.Country;
import com.ocr.ocrbackend.app.domain.user.User;
import com.ocr.ocrbackend.app.domain.user.UserOTP;
import com.ocr.ocrbackend.app.domain.user.UserOTPId;
import com.ocr.ocrbackend.app.exception.*;
import com.ocr.ocrbackend.app.repository.onboarding.UserOTPRepository;
import com.ocr.ocrbackend.app.repository.onboarding.UserRepository;
import com.ocr.ocrbackend.app.service.auth.SecurityService;
import com.ocr.ocrbackend.app.service.util.GenerateOTPUtil;
import com.ocr.ocrbackend.app.web.rest.dto.request.onboarding.VerifyOTPDTO;
import com.ocr.ocrbackend.app.web.rest.dto.response.auth.LoginDTO;
import io.getstream.chat.java.exceptions.StreamException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static com.ocr.ocrbackend.app.web.validation.ContactNumberValidator.validateContactNumber;
import static com.ocr.ocrbackend.app.web.validation.OTPExpirationValidator.validateIfOTPHasExpired;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class LoginService {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final UserOTPRepository userOTPRepository;

    @NonNull
    private final OTPSenderService otpSenderService;

    @NonNull
    private final SecurityService securityService;


    @Autowired
    private Environment environment;

    @Transactional
    public void sendOTP(String contactNumber, int countryCode)
            throws IOException, InvalidContactNumberException, InvalidCountryCodeException {
        validateContactNumberAndCountryCode(contactNumber, countryCode);

        boolean isProd= false;
        String[] profiles = environment.getActiveProfiles();
        if(profiles!=null && Arrays.stream(profiles).anyMatch(
                env -> (env.equalsIgnoreCase("prod")))){
            isProd=true;
        }

        String secureOTP = "000000";
        if(isProd){
            secureOTP = GenerateOTPUtil.generateSecureOTP();
        }
        UserOTP userOTP = userOTPRepository.findById(new UserOTPId(contactNumber, countryCode)).orElse(null);

        if (userOTP == null) {
            userOTP = buildUserOTP(contactNumber, countryCode, secureOTP);
        } else {
            userOTP.setLatestOtp(secureOTP);
        }

        userOTP.setLatestOtpTimestamp(ZonedDateTime.now());
        if(isProd){
            otpSenderService.sendOTP(contactNumber, countryCode, secureOTP);
        }
        userOTPRepository.save(userOTP);
    }

    private void validateContactNumberAndCountryCode(String contactNumber, int countryCode)
            throws InvalidContactNumberException, InvalidCountryCodeException {
        if (!StringUtils.isNotBlank(contactNumber) && validateContactNumber(contactNumber)) {
            throw new InvalidContactNumberException(contactNumber);
        }

        if (Country.getIfPresent(countryCode) == null) {
            throw new InvalidCountryCodeException(countryCode);
        }
    }

    private UserOTP buildUserOTP(String contactNumber, int countryCode, String secureOTP) {
        return UserOTP.builder()
                .contactNumber(contactNumber)
                .countryCode(countryCode)
                .latestOtp(secureOTP)
                .build();
    }

    @Transactional
    public LoginDTO verifyOTP(VerifyOTPDTO verifyOTPDTO)
            throws UserOTPNotFoundException, OTPTimedOutException, InvalidOTPException, InvalidContactNumberException,
            InvalidCountryCodeException, StreamException {
        String otp = verifyOTPDTO.getOtp();
        String contactNumber = verifyOTPDTO.getContactNumber();
        int countryCode = verifyOTPDTO.getCountryCode();
        Country country = Country.getIfPresent(countryCode);

        if (!validateContactNumber(contactNumber)) {
            throw new InvalidContactNumberException(contactNumber);
        }

        UserOTP userOTP = userOTPRepository.findById(new UserOTPId(contactNumber, countryCode)).orElse(null);
        User user = userRepository.findByContactNumberAndCountry(contactNumber,
                Country.getIfPresent(countryCode)).orElse(null);
        validateUserOTP(contactNumber, countryCode, userOTP, otp);

        User referrer = null;

        if (user == null) {
            User newUser = buildNewUser(contactNumber, country, referrer != null ? referrer.getId() : null);
            user = userRepository.save(newUser);
        }

        String authToken = securityService.getAuthToken(user.getId());

        return LoginDTO.of(user, authToken);
    }

    private void validateUserOTP(String contactNumber, int countryCode, UserOTP userOTP, String otp)
            throws UserOTPNotFoundException, OTPTimedOutException, InvalidOTPException {
        if (userOTP == null) {
            throw new UserOTPNotFoundException(contactNumber, countryCode);
        }

        if (validateIfOTPHasExpired(userOTP.getLatestOtpTimestamp())) {
            throw new OTPTimedOutException();
        }

        if (!userOTP.getLatestOtp().equals(otp)) {
            throw new InvalidOTPException();
        }
    }

    private User buildNewUser(String contactNumber, Country country, Long referrer) {
        return User.builder()
                .contactNumber(contactNumber)
                .country(country)
                .createdAt(ZonedDateTime.now())
                .build();
    }
}
