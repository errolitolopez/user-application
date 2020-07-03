package com.lorre.userapplication.exception.userregistrationtoken;

public class UserRegistrationTokenExpiredException extends RuntimeException {
    public UserRegistrationTokenExpiredException(String message) {
        super(message);
    }
}
