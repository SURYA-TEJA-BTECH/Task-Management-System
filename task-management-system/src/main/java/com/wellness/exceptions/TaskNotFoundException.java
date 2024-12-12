package com.wellness.exceptions;

public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException(String string) {
		super(string);
	}

	
	private static final long serialVersionUID = 1L;

}
