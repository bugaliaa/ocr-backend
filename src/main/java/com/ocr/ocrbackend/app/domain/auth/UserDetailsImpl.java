package com.ocr.ocrbackend.app.domain.auth;

import com.ocr.ocrbackend.app.domain.enums.UserRole;
import com.ocr.ocrbackend.app.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final List<GrantedAuthority> authorities;

    public UserDetailsImpl(String username, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public static UserDetails of(User user) {
        List<GrantedAuthority> authorities = Arrays.stream(UserRole.values())
                .map(userRole -> new SimpleGrantedAuthority(
                        userRole.getRole()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(String.valueOf(user.getId()), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
