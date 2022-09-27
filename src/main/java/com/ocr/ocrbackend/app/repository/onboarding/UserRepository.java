package com.ocr.ocrbackend.app.repository.onboarding;

import com.ocr.ocrbackend.app.domain.enums.Country;
import com.ocr.ocrbackend.app.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByContactNumberAndCountry(String contactNumber, Country country);
}
