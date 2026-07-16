package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.Details;
import com.api.request.model.Search;
import com.api.services.DashboardService;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

@Listeners(com.listeners.ApiTestListerner.class)

public class SearchApiTest {
	
	
	private JobService jobService;
	private static final String JOB_SEARCH_ID="JOB_338666";
	private Search searchPayload;
	
	@BeforeMethod(description="instantiating service service")
	public void setUp() {
		 searchPayload= new Search(JOB_SEARCH_ID);
		
		jobService= new JobService();
}
	
	
	@Test(description="verify search api is working fine",groups= {"Regression","smoke"})
	public void verifySearchApi() {
		jobService.Search(Role.FD, searchPayload).then().spec(SpecUtil.responseSpec_OK());
		
	}

}
