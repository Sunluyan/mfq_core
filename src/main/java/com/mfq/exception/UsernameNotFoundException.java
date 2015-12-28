package com.mfq.exception;

public class UsernameNotFoundException extends AuthenticationException {

	private static final long serialVersionUID = -4932444002852078675L;

	public UsernameNotFoundException() {
	}

	public UsernameNotFoundException(String msg) {
		super(msg);
	}
}