package com.zenefits.bizlayer.restapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zenefits.bizlayer.restapi.exceptions.ApplicationException;
import com.zenefits.bizlayer.restapi.response.EmpResponse;
import com.zenefits.bizlayer.restapi.response.Employee;
import com.zenefits.bizlayer.restapi.service.GenerateEmployeeListHandler;
import com.zenefits.bizlayer.restapi.util.CollectionUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/employees")
@Api(value = "/orgstructure")
public class ThirdPartyResource {

	@GET
	@Path("/{companyId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation("API for sending list of employees to client")
	public EmpResponse getEmployeeList(@PathParam("companyId") String companyId) throws Exception {

		EmpResponse empResponse = new EmpResponse();
		try {
			GenerateEmployeeListHandler listGenerationService = new GenerateEmployeeListHandler(companyId);
			List<Employee> lstEmployee = listGenerationService.generateList();
			if (!CollectionUtil.isNullorEmpty(lstEmployee)) {
				empResponse.setStatus(200);
				empResponse.setLstEmployee(lstEmployee);
			} else {

			}
		} catch (ApplicationException ae) {
			empResponse.setError(ae.getDescription());
			empResponse.setStatus(ae.getErrorcode());
		}
		return empResponse;
	}
}
