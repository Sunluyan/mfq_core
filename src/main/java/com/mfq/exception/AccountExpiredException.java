package com.mfq.exception;

public class AccountExpiredException extends AccountStatusException {
	
	private static final long serialVersionUID = -3466162245845891755L;

	public AccountExpiredException() {
	}

	public AccountExpiredException(String msg) {
		super(msg);
	}

	public AccountExpiredException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}