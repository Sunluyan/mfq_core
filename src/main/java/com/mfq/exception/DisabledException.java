package com.mfq.exception;

public class DisabledException extends AccountStatusException {
	
	private static final long serialVersionUID = -1675077221546556332L;

	public DisabledException() {
	}

	public DisabledException(String msg) {
		super(msg);
	}

	public DisabledException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}