package com.zenefits.bizlayer.restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenefits.bizlayer.restapi.exceptions.ApplicationException;
import com.zenefits.bizlayer.restapi.exceptions.ExceptionType;
import com.zenefits.bizlayer.restapi.response.Employee;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.adapter.ConnectorAdapter;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response.BaseRequest;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response.EmployeeRequest;
import com.zenefits.bizlayer.restapi.util.CollectionUtil;
import com.zenefits.bizlayer.restapi.util.DeserializerUtil;
import com.zenefits.bizlayer.restapi.util.StringUtil;

public class GenerateEmployeeListHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateEmployeeListHandler.class);

	private String url;
	private ConnectorAdapter connectorAdapter;

	public GenerateEmployeeListHandler(String companyId) {
		this.url = "https://api.zenefits.com/core/companies/XX/people".replace("XX", companyId);
		this.connectorAdapter = new ConnectorAdapter("Bearer UFrtE725SeWv0QZrUHPk");
	}

	public List<Employee> generateList() throws ApplicationException {

		boolean callAgain = true;
		String response = null;
		List<Employee> lstEmployee = new ArrayList<>();
		BaseRequest baseRequest = null;

		while (callAgain) {
			response = connectorAdapter.getThirdPartyResponse(url);
			if (!StringUtil.isNullorEmpty(response)) {
				baseRequest = DeserializerUtil.deserialize(response);
				if (baseRequest.getStatus() == 200 || StringUtil.isNullorEmpty(baseRequest.getError())) {
					addEmployeeToList(baseRequest, lstEmployee);
					if (baseRequest.getData().getNext_url() != null
							&& !(baseRequest.getData().getNext_url().isEmpty())) {
						callAgain = true;
						url = baseRequest.getData().getNext_url();
					} else {
						callAgain = false;
					}
				} else {
					LOGGER.error("3rd Party Api response code is not 200");
					throw new ApplicationException(ExceptionType.RESPONSEERROR,
							StringUtil.isNullorEmpty(baseRequest.getError()) == true
									? ExceptionType.RESPONSEERROR.toString().toLowerCase() : baseRequest.getError(),
							baseRequest.getStatus());
				}

			}
		}
		fillReporteesInEmployee(lstEmployee);

		return lstEmployee.stream().filter(e -> !"Admin".equalsIgnoreCase(e.getPreferredName()))
				.collect(Collectors.toList());

	}

	private void addEmployeeToList(BaseRequest baseRequest, List<Employee> lstEmployee) {
		if (baseRequest.getData().getData() != null && !baseRequest.getData().getData().isEmpty()) {
			for (EmployeeRequest empReq : baseRequest.getData().getData()) {
				int managerId = -1;
				if (empReq.getManager().getUrl() != null) {
					String[] managerUrl = empReq.getManager().getUrl().split("/");
					managerId = Integer.valueOf(managerUrl[managerUrl.length - 1]);
				}
				lstEmployee.add(new Employee(empReq.getLast_name(), empReq.getPreferred_name(),
						Integer.valueOf(empReq.getId()), managerId));
			}
		}
	}

	private void fillReporteesInEmployee(List<Employee> lstEmployee) {
		if (!CollectionUtil.isNullorEmpty(lstEmployee)) {
			for (Employee emp : lstEmployee) {
				if (emp.getManagerId() != -1) {
					Employee manager = lstEmployee.stream().filter(e -> e.getId() == emp.getManagerId()).findFirst()
							.get();
					if (manager.getReportees() == null)
						manager.setReportees(new ArrayList<>());
					manager.getReportees().add(emp.getId());
				}
			}
		}

	}

}
