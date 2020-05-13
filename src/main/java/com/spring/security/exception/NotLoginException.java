package com.spring.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description:
 */
public class NotLoginException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public NotLoginException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotLoginException(String msg) {
        super(msg);
    }
}
