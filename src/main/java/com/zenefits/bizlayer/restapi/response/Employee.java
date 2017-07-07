package com.zenefits.bizlayer.restapi.response;

import java.util.List;

public class Employee {

	private String lastName;

	private String preferredName;

	private int id;

	private int managerId;

	private List<Employee> reportees;

	public Employee(String lastName, String preferredName, int id, int managerId) {
		this.lastName = lastName;
		this.preferredName = preferredName;
		this.id = id;
		this.managerId = managerId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPreferredName() {
		return preferredName;
	}

	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public List<Employee> getReportees() {
		return reportees;
	}

	public void setReportees(List<Employee> reportees) {
		this.reportees = reportees;
	}

}
