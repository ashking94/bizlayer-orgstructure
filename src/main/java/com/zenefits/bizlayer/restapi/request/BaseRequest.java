package com.zenefits.bizlayer.restapi.request;

public class BaseRequest {

	private int status;

	private RequestData requestData;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public RequestData getRequestData() {
		return requestData;
	}

	public void setRequestData(RequestData requestData) {
		this.requestData = requestData;
	}

}
