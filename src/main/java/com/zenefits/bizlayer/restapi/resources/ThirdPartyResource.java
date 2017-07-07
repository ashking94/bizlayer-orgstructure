package com.zenefits.bizlayer.restapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zenefits.bizlayer.restapi.response.Employee;
import com.zenefits.bizlayer.restapi.service.GenerateEmployeeListHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/employees")
@Api(value = "/v1")
public class ThirdPartyResource {

	@GET
	@Path("/{companyId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation("API for sending list of employees to client")
	public List<Employee> getEmployeeList(@PathParam("companyId") String companyId) throws Exception {

		GenerateEmployeeListHandler listGenerationService = new GenerateEmployeeListHandler(companyId);
		return listGenerationService.generateList();

	}
}
