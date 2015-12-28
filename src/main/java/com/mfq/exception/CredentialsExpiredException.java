package com.mfq.exception;

public class CredentialsExpiredException extends AccountStatusException {
	
	private static final long serialVersionUID = 3850364728055448269L;

	public CredentialsExpiredException() {
	}

	public CredentialsExpiredException(String msg) {
		super(msg);
	}

	public CredentialsExpiredException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}