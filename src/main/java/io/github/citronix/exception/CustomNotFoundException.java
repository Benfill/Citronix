package io.github.citronix.exception;

public class CustomNotFoundException extends RuntimeException {

	private String msg;

	public CustomNotFoundException() {
	}

	public CustomNotFoundException(String message) {
		super(message);
		this.msg = message;
	}

}
