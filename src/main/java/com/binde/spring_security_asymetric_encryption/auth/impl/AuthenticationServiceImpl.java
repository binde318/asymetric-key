package com.binde.spring_security_asymetric_encryption.auth.impl;

import com.binde.spring_security_asymetric_encryption.auth.AuthenticationService;
import com.binde.spring_security_asymetric_encryption.auth.request.AuthenticationRequest;
import com.binde.spring_security_asymetric_encryption.auth.request.RefreshRequest;
import com.binde.spring_security_asymetric_encryption.auth.request.RegistrationRequest;
import com.binde.spring_security_asymetric_encryption.response.AuthenticationResponse;
import com.binde.spring_security_asymetric_encryption.role.RoleRepository;
import com.binde.spring_security_asymetric_encryption.security.JwtService;
import com.binde.spring_security_asymetric_encryption.user.UserMapper;
import com.binde.spring_security_asymetric_encryption.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private UserMapper userMapper;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        return null;
    }

    @Override
    public void register(RegistrationRequest request) {

    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) {
        return null;
    }
}
