package com.wellness.exceptions;

public class NoTasksPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoTasksPresentException() {
		super("No tasks are present.");
	}
}
