package com.rstyles.util.sql;

public class IllegalFormatException extends RuntimeException {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public IllegalFormatException() {
		super();
	}

	public IllegalFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalFormatException(String message) {
		super(message);
	}

	public IllegalFormatException(Throwable cause) {
		super(cause);
	}

}
