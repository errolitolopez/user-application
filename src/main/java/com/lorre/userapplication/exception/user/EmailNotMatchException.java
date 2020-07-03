package com.lorre.userapplication.exception.user;

public final class EmailNotMatchException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailNotMatchException(String message) {
        super(message);
    }
}
