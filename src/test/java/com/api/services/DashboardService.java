package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

import static com.api.utils.SpecUtil.*;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DashboardService {
	
	
	private static final String COUNT_ENDPOINT="/dashboard/count";
	private static final String DETAIL_ENDPOINT="/dashboard/details";
	private static final Logger LOGGER= LogManager.getLogger(DashboardService.class);

	@Step("making api request for the role")
	public Response count(Role role) {
		LOGGER.info("making api request to {} for the role {}",COUNT_ENDPOINT,role);

		Response response=given().spec(requestSpecWithAuth(role))
		.when().get("/dashboard/count");
		
		return response;
	}
	
	@Step("making api request without auth token")
	public Response countWithoutAuth() {
		LOGGER.info("making api request to {} with no auth token",COUNT_ENDPOINT);

		Response response=given().spec(requestSpec()).
		when().get("/dashboard/count");
		System.out.println("response is"+response.jsonPath().getString("message"));
		
		return response;
	}
	
	@Step("making api request with role and payload")
	public Response details(Role role,Object payload) {
		LOGGER.info("making api request to {} with role {} and paload {}",DETAIL_ENDPOINT,role,payload);

		Response response=given().spec(requestSpecWithAuth(role,payload))
		.when().post(DETAIL_ENDPOINT);
		
		return response;
	}


}
