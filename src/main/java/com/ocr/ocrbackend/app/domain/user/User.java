package com.ocr.ocrbackend.app.domain.user;

import com.ocr.ocrbackend.app.domain.enums.Country;
import com.ocr.ocrbackend.app.domain.enums.UserRole;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Country country = Country.INDIA;

    @Column(name = "contact_number", unique = true, updatable = false, nullable = false, length = 10)
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid contact number")
    private String contactNumber;

    //TODO: validate the email using regex while updating. This is not guaranteed to validate properly.
    @Column(name = "email", unique = true)
    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "role")
    private UserRole role = UserRole.USER;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "last_modified")
    private ZonedDateTime lastModified;
}
