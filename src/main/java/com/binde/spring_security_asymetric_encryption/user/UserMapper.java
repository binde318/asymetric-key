package com.binde.spring_security_asymetric_encryption.user;

import com.binde.spring_security_asymetric_encryption.user.request.ProfileUpdateRequest;
import io.micrometer.common.util.StringUtils;

public class UserMapper {

    public void mergeUserInfo( final User user,final ProfileUpdateRequest request) {
        if (StringUtils.isNotBlank(request.getFirstName())
                && !user.getFirstName().equals(request.getLastName())) {
          user.setFirstName(request.getFirstName());

        }
        if (StringUtils.isNotBlank(request.getLastName())
                && !user.getLastName().equals(request.getLastName())) {
            user.setLastName(request.getFirstName());

        }
        if (request.getDateOfBirth() != null
                && !request.getDateOfBirth().equals(user.getDateOfBirth())) {
            user.setDateOfBirth(request.getDateOfBirth().atStartOfDay());
        }
    }

}
