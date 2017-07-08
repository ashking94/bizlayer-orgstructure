package com.zenefits.bizlayer.restapi.exceptions;

public enum ExceptionType {

	THIRDPARTYERROR(1001), DESERIALIZATIONERROR(1002), RESPONSEERROR(1003);

	private int code;

	private ExceptionType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
