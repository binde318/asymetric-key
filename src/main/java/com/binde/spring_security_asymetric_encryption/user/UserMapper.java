package com.binde.spring_security_asymetric_encryption.user;

import com.binde.spring_security_asymetric_encryption.auth.request.RegistrationRequest;
import com.binde.spring_security_asymetric_encryption.user.request.ProfileUpdateRequest;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User toUser(RegistrationRequest request) {

      return User.builder()
              .firstName(request.getFirstName())
              .lastName(request.getLastName())
              .email(request.getEmail())
              .phoneNumber(request.getPhoneNumber())
              .password(this.passwordEncoder.encode(request.getPassword()))
              .enabled(true)
              .locked(false)
              .credentialsExpired(false)
              .emailVerified(false)
              .phoneNumberVerified(false)
                .build();
    }

    public void mergeUserInfo(final User user, final ProfileUpdateRequest request) {
        if (StringUtils.isNotBlank(request.getFirstName())
                && !user.getFirstName().equals(request.getFirstName())) {
          user.setFirstName(request.getFirstName());

        }
        if (StringUtils.isNotBlank(request.getLastName())
                && !user.getLastName().equals(request.getLastName())) {
            user.setLastName(request.getLastName());

        }
        if (request.getDateOfBirth() != null
                && !request.getDateOfBirth().equals(user.getDateOfBirth())) {
            user.setDateOfBirth(request.getDateOfBirth().atStartOfDay());
        }
    }

}
