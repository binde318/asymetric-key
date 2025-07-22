package com.binde.spring_security_asymetric_encryption.auth;

import com.binde.spring_security_asymetric_encryption.auth.request.AuthenticationRequest;
import com.binde.spring_security_asymetric_encryption.auth.request.RefreshRequest;
import com.binde.spring_security_asymetric_encryption.auth.request.RegistrationRequest;
import com.binde.spring_security_asymetric_encryption.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request);
    void register(RegistrationRequest request);
    AuthenticationResponse refreshToken(RefreshRequest request);

}
