package com.lorre.userapplication.exception.user;

public class UsernameNotMatchException extends RuntimeException {
    public UsernameNotMatchException(String message) {
        super(message);
    }
}
