package com.zenefits.bizlayer.restapi.service;

import java.util.ArrayList;
import java.util.List;

import com.zenefits.bizlayer.restapi.response.Employee;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.adapter.ConnectorAdapter;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response.BaseRequest;
import com.zenefits.bizlayer.restapi.thirdpartyaccesslayer.response.EmployeeRequest;
import com.zenefits.bizlayer.restapi.util.DeserializerUtil;

public class GenerateEmployeeListHandler {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(GenerateEmployeeListHandler.class);

	private String url;
	private ConnectorAdapter connectorAdapter;

	public GenerateEmployeeListHandler(String companyId) {
		this.url = "https://api.zenefits.com/core/companies/XX/people".replace("XX", companyId);
		this.connectorAdapter = new ConnectorAdapter("Bearer UFrtE725SeWv0QZrUHPk");
	}

	public List<Employee> generateList() throws Exception {

		boolean callAgain = true;
		String response = null;
		List<Employee> lstEmployee = new ArrayList<>();
		BaseRequest baseRequest = null;

		while (callAgain) {
			response = connectorAdapter.getThirdPartyResponse(url);
			if (response != null && !response.isEmpty()) {
				baseRequest = DeserializerUtil.deserialize(response);
				addEmployeeToList(baseRequest, lstEmployee);
				if (baseRequest.getData().getNext_url() != null && !(baseRequest.getData().getNext_url().isEmpty())) {
					callAgain = true;
					url = baseRequest.getData().getNext_url();
				} else {
					callAgain = false;
				}
			}
		}
		fillReporteesInEmployee(lstEmployee);
		return lstEmployee;

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
		for (Employee emp : lstEmployee) {
			if (emp.getManagerId() != -1) {
				Employee manager = lstEmployee.stream().filter(e -> e.getId() == emp.getManagerId()).findFirst().get();
				if (manager.getReportees() == null)
					manager.setReportees(new ArrayList<>());
				manager.getReportees().add(emp.getId());
			}
		}
	}

}
