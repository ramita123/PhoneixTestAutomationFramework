package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

import static com.api.utils.SpecUtil.*;

import io.restassured.response.Response;

public class DashboardService {
	
	
	private static final String COUNT_ENDPOINT="/dashboard/count";
	private static final String DETAIL_ENDPOINT="/dashboard/details";
	
	public Response count(Role role) {
		Response response=given().spec(requestSpecWithAuth(role))
		.when().get("/dashboard/count");
		
		return response;
	}
	
	public Response countWithoutAuth() {
		Response response=given().spec(requestSpec()).
		when().get("/dashboard/count");
		System.out.println("response is"+response.jsonPath().getString("message"));
		
		return response;
	}
	
	
	public Response details(Role role,Object payload) {
		Response response=given().spec(requestSpecWithAuth(role,payload))
		.when().post(DETAIL_ENDPOINT);
		
		return response;
	}


}
