package com.zenefits.bizlayer.restapi.request;

import java.util.List;

public class RequestData {

	private String nextUrl;

	private List<Employee> lstEmployee;

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public List<Employee> getLstEmployee() {
		return lstEmployee;
	}

	public void setLstEmployee(List<Employee> lstEmployee) {
		this.lstEmployee = lstEmployee;
	}

}
