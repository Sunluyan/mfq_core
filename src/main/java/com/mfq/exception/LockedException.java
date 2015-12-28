package com.mfq.exception;

public class LockedException extends AccountStatusException {

	private static final long serialVersionUID = 7676192866026495533L;

	public LockedException() {
	}

	public LockedException(String msg) {
		super(msg);
	}

	public LockedException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}