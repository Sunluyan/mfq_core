package com.mfq.exception;

public class AccountStatusException extends AuthenticationException {

	private static final long serialVersionUID = -9032317198711822671L;

	public AccountStatusException() {
	}

	public AccountStatusException(String msg) {
		super(msg);
	}

	public AccountStatusException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}