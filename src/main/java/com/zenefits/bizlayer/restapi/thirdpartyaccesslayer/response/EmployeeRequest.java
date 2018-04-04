package com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeRequest {

	private String last_name;

	private String preferred_name;

	private Manager manager;

	private String id;

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPreferred_name() {
		return preferred_name;
	}

	public void setPreferred_name(String preferred_name) {
		this.preferred_name = preferred_name;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
