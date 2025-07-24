package com.binde.spring_security_asymetric_encryption.auth.impl;

import com.binde.spring_security_asymetric_encryption.auth.AuthenticationService;
import com.binde.spring_security_asymetric_encryption.auth.request.AuthenticationRequest;
import com.binde.spring_security_asymetric_encryption.auth.request.RefreshRequest;
import com.binde.spring_security_asymetric_encryption.auth.request.RegistrationRequest;
import com.binde.spring_security_asymetric_encryption.exception.BusinessException;
import com.binde.spring_security_asymetric_encryption.exception.ErrorCode;
import com.binde.spring_security_asymetric_encryption.response.AuthenticationResponse;
import com.binde.spring_security_asymetric_encryption.role.Role;
import com.binde.spring_security_asymetric_encryption.role.RoleRepository;
import com.binde.spring_security_asymetric_encryption.security.JwtService;
import com.binde.spring_security_asymetric_encryption.user.User;
import com.binde.spring_security_asymetric_encryption.user.UserMapper;
import com.binde.spring_security_asymetric_encryption.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        final Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        final User user = (User) auth.getPrincipal();
        final String token = this.jwtService.generateAccessToken(user.getUsername());
        final String refreshToken = this.jwtService.generateTokenRefresh(user.getUsername());
        final String tokenType= "Bearer";
        return AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .tokenType(tokenType)
                .build();

    }

    @Override
    @Transactional
    public void register(RegistrationRequest request) {
        checkUserEmail(request.getEmail());
        checkUserPhoneNumber(request.getPhoneNumber());
        checkUserPassword(request.getPassword(),request.getConfirmedPassword());

        final Role userRole = this.roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException("Role user does not exist"));

        final List<Role> roles = new ArrayList<>();
        roles.add(userRole);

        final User user = UserMapper.toUser(request);
        user.setRoles(roles);
        log.debug("saving user {}", user);
        this.userRepository.save(user);

        final List<User> users = new ArrayList<>();
        users.add(user);
        userRole.setUsers(users);
        this.roleRepository.save(userRole);
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) {
        final String newAccessToken = this.jwtService.refreshAccessToken(request.getRefreshToken());
        final String tokenType = "Bearer";
        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.getRefreshToken())
                .tokenType(tokenType)
                .build();

    }
    private void checkUserEmail(final String email) {
        final boolean emailExist = this.userRepository.existsByEmailIgnoreCase(email);
        if (emailExist){
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXIST);
        }

    }
    private void checkUserPhoneNumber(final String phoneNumber) {
        final boolean phoneExist= this.userRepository.existsByPhoneNumber(phoneNumber);
        if (phoneExist){
            throw new BusinessException(ErrorCode.PHONE_NUMBER_ALREADY_EXIST);
        }
    }
    private void checkUserPassword(final String password, final String confirmedPassword) {
        if (password == null || !password.equals(confirmedPassword)){
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }
    }

}
