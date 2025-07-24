package com.binde.spring_security_asymetric_encryption.user.impl;

import com.binde.spring_security_asymetric_encryption.exception.BusinessException;
import com.binde.spring_security_asymetric_encryption.exception.ErrorCode;
import com.binde.spring_security_asymetric_encryption.user.User;
import com.binde.spring_security_asymetric_encryption.user.UserMapper;
import com.binde.spring_security_asymetric_encryption.user.UserRepository;
import com.binde.spring_security_asymetric_encryption.user.UserService;
import com.binde.spring_security_asymetric_encryption.user.request.ChangePasswordRequest;
import com.binde.spring_security_asymetric_encryption.user.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername( final String userEmail) throws UsernameNotFoundException {
        return this.userRepository.findByEmailIgnoreCase(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userEmail));
    }
    @Override
    public void updateProfileInfo(ProfileUpdateRequest request, String userid) {
        User savedUser = this.userRepository.findById(userid)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userid));
        this.userMapper.mergeUserInfo(savedUser,request);
        userRepository.save(savedUser);

    }

    @Override
    public void changePassword(ChangePasswordRequest request,final String userid) {
    if (!request.getNewPassword().equals(request.getConfirmNewPassword())){
        throw new BusinessException(ErrorCode.CHANGE_PASSWORD_MISMATCH);
    }
   final User savedUser = userRepository.findById(userid)
           .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userid));
    if (!this.passwordEncoder.matches(request.getCurrentPassword(),
            savedUser.getPassword())){
        throw new BusinessException(ErrorCode.INVALID_CURRENT_PASSWORD);
    }
    final String encoded = passwordEncoder.encode(request.getNewPassword());
    savedUser.setPassword(encoded);
    this.userRepository.save(savedUser);
    }

    @Override
    public void deactivateAccount(String userid) {
        final User user = this.userRepository.findById(userid)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userid));
        if (!user.isEnabled()){
            throw new BusinessException(ErrorCode.ACCOUNT_ALREADY_DEACTIVATED);
        }
        user.setEnabled(false);
        this.userRepository.save(user);

    }

    @Override
    public void reactivateAccount(String userid) {
        final  User user = this.userRepository.findById(userid)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userid));
        if (user.isEnabled()){
            throw new BusinessException(ErrorCode.ACCOUNT_ALREADY_ACTIVATED);
        }
        user.setEnabled(true);
        this.userRepository.save(user);

    }

    @Override
    public void deleteAccount(String userid) {
        //this method need the rest of the entities
        //the logic is just to schedule a profile for deletion
        //and the scheduled job to pick the profiles and delete everything

    }


}
