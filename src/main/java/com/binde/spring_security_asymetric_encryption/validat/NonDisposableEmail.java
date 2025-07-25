package com.binde.spring_security_asymetric_encryption.validat;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailDomainValidator.class)
public @interface NonDisposableEmail {

    String message() default "Disposable email are not allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
