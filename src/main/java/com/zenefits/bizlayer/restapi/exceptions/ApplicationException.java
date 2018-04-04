package com.zenefits.bizlayer.restapi.exceptions;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	private ExceptionType exceptionType;
	private String description;
	private int errorcode;

	public ApplicationException(ExceptionType exceptionType, String description, int errorcode) {
		this.exceptionType = exceptionType;
		this.description = description;
		this.errorcode = errorcode;
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public String getDescription() {
		return description;
	}

	public int getErrorcode() {
		return errorcode;
	}

}
