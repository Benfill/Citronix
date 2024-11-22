package io.github.citronix.exception;

public class Validation extends RuntimeException {

	private String msg;

	public Validation() {
	}

	public Validation(String message) {
		super(message);
		this.msg = message;
	}

}