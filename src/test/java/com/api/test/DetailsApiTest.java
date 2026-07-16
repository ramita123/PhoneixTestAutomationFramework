package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.Details;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;
@Listeners(com.listeners.ApiTestListerner.class)

public class DetailsApiTest {
	private DashboardService dashboardService;
	private Details  detailsPayload;
	
	@BeforeMethod(description="instantiating dashboard service")
	public void setUp() {
		detailsPayload= new Details("created_today");
	 dashboardService= new DashboardService();
}
	
	
	@Test
	public void verifyDashboardDetailsApi() {
		dashboardService.details(Role.FD, detailsPayload).then().spec(SpecUtil.responseSpec_OK());
	}
	

}
