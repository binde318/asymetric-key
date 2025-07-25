package com.binde.spring_security_asymetric_encryption.auth.request;

import com.binde.spring_security_asymetric_encryption.validat.NonDisposableEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {
    @NotBlank(message = "VALIDATION.REGISTRATION.FIRSTNAME.NOT_BLANK")
    @Size(
            min = 1,
            max = 56,
            message = "VALIDATION.REGISTRATION.FIRSTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.FIRSTNAME.SIZE"
    )
    @Schema(example = "Binde")
    private String firstName;
    @NotBlank(message = "VALIDATION.REGISTRATION.LASTNAME.NOT_BLANK")
    @Size(
            min = 1,
            max = 56,
            message = "VALIDATION.REGISTRATION.LASTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.LASTNAME.SIZE"
    )
    @Schema(example = "Binde")
    private String lastName;
    @NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.NOT_BLANK" )
    @Email(message ="VALIDATION.REGISTRATION.EMAIL.DISPOSABLE" )
    @NonDisposableEmail(message="VALIDATION.REGISTRATION.EMAIL.DISPOSABLE")
    @Schema(example = "binde@gmail.com")
    private String email;
    @NotBlank(message = "VALIDATION.REGISTRATION.PHONE.NOT_BLANK")
    @Pattern(
            regexp = "^\\+?[0-9]{10,13}",
            message = "VALIDATION.REGISTRATION.PHONE.FORMAT"
    )
    @Schema(example = "+23481679110793")
    private String phoneNumber;
        @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
        @Size(
                min = 8,
                max = 50,
        message = "VALIDATION.REGISTRATION.PASSWORD.SIZE"
        )
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\w).*$",
            message = "VALIDATION.REGISTRATION.PASSWORD.WEAK")
    @Schema(example = "<PASSWORD>")
    private String password;
    @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
    @Size(
            min = 8,
            max = 50,
            message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.SIZE"
    )
    @Schema(example = "<PASSWORD>")
    private String confirmedPassword;

}
