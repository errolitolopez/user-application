package com.lorre.userapplication.exception.user;

public class EmailNotMatchException extends RuntimeException {
    public EmailNotMatchException(String message) {
        super(message);
    }
}
