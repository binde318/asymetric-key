package com.binde.spring_security_asymetric_encryption.user;

import com.binde.spring_security_asymetric_encryption.user.request.ChangePasswordRequest;
import com.binde.spring_security_asymetric_encryption.user.request.ProfileUpdateRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void updateProfileInfo(ProfileUpdateRequest request, String userid);
    void changePassword(ChangePasswordRequest request, String userid);
    void deactivateAccount(String userid);
    void reactivateAccount(String userid);
    void deleteAccount(String userid);
}
