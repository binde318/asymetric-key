package com.binde.spring_security_asymetric_encryption.user.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordRequest {
    public String currentPassword;
    public String newPassword;
    public String confirmNewPassword;
}
