package com.mfq.exception;

public class AuthenticationException extends Exception {

	private static final long serialVersionUID = -6838759357761945741L;

	private Object extraInformation;

	public AuthenticationException() {
	}

	public AuthenticationException(String msg) {
		super(msg);
	}

	public AuthenticationException(String msg, Object extraInformation) {
		super(msg);
		this.extraInformation = extraInformation;
	}

	public Object getExtraInformation() {
		return this.extraInformation;
	}

	public void clearExtraInformation() {
		this.extraInformation = null;
	}
}