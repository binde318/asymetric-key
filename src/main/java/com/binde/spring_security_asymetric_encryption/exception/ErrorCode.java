package com.binde.spring_security_asymetric_encryption.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND","User not found with the id %s",NOT_FOUND),
    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH", "Current password and new password are not the same",BAD_REQUEST ),
    INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD","Current password is incorrect" ,BAD_REQUEST ),
    ACCOUNT_ALREADAY_DEACCTIVATED("ACCOUNT_ALREADY_DEACCTIVATED","Account already deacctived" ,BAD_REQUEST ),
    ACCOUNT_ALREADAY_ACCTIVATED("ACCOUNT_ALREADY_ACCIVATED","Account already accitivated" ,BAD_REQUEST );
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
