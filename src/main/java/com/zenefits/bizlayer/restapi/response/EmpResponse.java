package com.zenefits.bizlayer.restapi.response;

import java.util.List;

public class EmpResponse {

	private int status;

	private List<Employee> lstEmployee;

	private String error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Employee> getLstEmployee() {
		return lstEmployee;
	}

	public void setLstEmployee(List<Employee> lstEmployee) {
		this.lstEmployee = lstEmployee;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
