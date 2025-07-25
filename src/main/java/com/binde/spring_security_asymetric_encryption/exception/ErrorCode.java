package com.binde.spring_security_asymetric_encryption.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND","User not found with the id %s",NOT_FOUND),
    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH", "Current password and new password are not the same",BAD_REQUEST ),
    INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD","Current password is incorrect" ,BAD_REQUEST ),
    ACCOUNT_ALREADY_DEACTIVATED("ACCOUNT_ALREADY_DEACTIVATED","Account already deactivated" ,BAD_REQUEST ),
    ACCOUNT_ALREADY_ACTIVATED("ACCOUNT_ALREADY_ACTIVATED","Account already activated" ,BAD_REQUEST ),
    EMAIL_ALREADY_EXIST("EMAIL_ALREADY_EXIST","Email Already Exist" ,BAD_REQUEST ),
    PHONE_NUMBER_ALREADY_EXIST("PHONE_NUMBER_ALREADY_EXIST","Phone Number Already Exist" ,BAD_REQUEST ),
    PASSWORD_MISMATCH("PASSWORD_MISMATCH","password mismatch" , BAD_REQUEST),
    ERR_USER_DISABLED("ERR_USER_DISABLED","user disabled" ,UNAUTHORIZED ),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS","invalid user credentials" ,BAD_REQUEST ),
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND","Cannot find user with the provided username" , NOT_FOUND),
    INTERNAL_EXCEPTION("INTERNAL_EXCEPTION","An internal exception occurred, please try again or contact the admin" ,INTERNAL_SERVER_ERROR );

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

    ErrorCode(final String code,
              final String defaultMessage,
              final HttpStatus status) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.status = status;
    }
}
