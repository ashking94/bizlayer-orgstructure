package com.zenefits.bizlayer.restapi.response;

import java.util.List;

import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response.EmployeeRequest;

public class Employee extends EmployeeRequest {

	private List<Employee> lstEmployee;

	public List<Employee> getLstEmployee() {
		return lstEmployee;
	}

	public void setLstEmployee(List<Employee> lstEmployee) {
		this.lstEmployee = lstEmployee;
	}

}
