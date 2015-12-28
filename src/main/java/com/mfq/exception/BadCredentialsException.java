package com.mfq.exception;

public class BadCredentialsException extends AuthenticationException {

	private static final long serialVersionUID = 3788382872365124092L;

	public BadCredentialsException() {
	}

	public BadCredentialsException(String msg) {
		super(msg);
	}
}