package com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestData {

	private String next_url;

	private List<EmployeeRequest> data;

	public String getNext_url() {
		return next_url;
	}

	public void setNext_url(String next_url) {
		this.next_url = next_url;
	}

	public List<EmployeeRequest> getData() {
		return data;
	}

	public void setData(List<EmployeeRequest> data) {
		this.data = data;
	}

}
