package com.ocr.ocrbackend.app.domain.auth;

import com.ocr.ocrbackend.app.repository.onboarding.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SimpleUserDetailsService implements UserDetailsService {

    @NonNull
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findById(Long.valueOf(userId))
                .map(UserDetailsImpl::of)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "A user with provided ID %s does not exists.".formatted(userId)));
    }
}
