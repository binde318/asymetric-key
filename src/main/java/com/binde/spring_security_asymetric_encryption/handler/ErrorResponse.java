package com.binde.spring_security_asymetric_encryption.handler;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ErrorResponse {
    private String message;
    private String code;
    private List<ValidationError> validationErrors;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
   public static class ValidationError{
        private String field;
        private String code;
        private String message;

    }
}
